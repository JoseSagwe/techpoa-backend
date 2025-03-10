package com.techpoa_backend.admin.controller;

import com.techpoa_backend.admin.dto.AccessCodeDTO;
import com.techpoa_backend.admin.services.AdminSecurityService;
import com.techpoa_backend.launch.dto.ContactMessageDTO;
import com.techpoa_backend.launch.dto.NewsletterSubscriberDTO;
import com.techpoa_backend.launch.dto.QuoteRequestDTO;
import com.techpoa_backend.launch.service.ContactService;
import com.techpoa_backend.launch.service.NewsletterService;
import com.techpoa_backend.launch.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminSecurityService securityService;
    private final NewsletterService newsletterService;
    private final QuoteService quoteService;
    private final ContactService contactService;

    @Autowired
    public AdminController(AdminSecurityService securityService,
                           NewsletterService newsletterService,
                           QuoteService quoteService,
                           ContactService contactService) {
        this.securityService = securityService;
        this.newsletterService = newsletterService;
        this.quoteService = quoteService;
        this.contactService = contactService;
    }

    @PostMapping("/verify")
    public ResponseEntity<Map<String, Object>> verifyAccessCode(@RequestBody AccessCodeDTO accessCodeDTO) {
        Map<String, Object> response = new HashMap<>();

        boolean isValid = securityService.validateAccessCode(accessCodeDTO.getAccessCode());

        if (isValid) {
            response.put("success", true);
            response.put("message", "Access granted");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "Invalid access code");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @GetMapping("/subscribers")
    public ResponseEntity<List<NewsletterSubscriberDTO>> getAllSubscribers(@RequestParam String accessCode) {
        if (!securityService.validateAccessCode(accessCode)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<NewsletterSubscriberDTO> subscribers = newsletterService.getAllSubscribers();
        return ResponseEntity.ok(subscribers);
    }

    @GetMapping("/quotes")
    public ResponseEntity<List<QuoteRequestDTO>> getAllQuotes(@RequestParam String accessCode) {
        if (!securityService.validateAccessCode(accessCode)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<QuoteRequestDTO> quotes = quoteService.getAllQuoteRequests();
        return ResponseEntity.ok(quotes);
    }

    @GetMapping("/contact-messages")
    public ResponseEntity<List<ContactMessageDTO>> getAllContactMessages(@RequestParam String accessCode) {
        if (!securityService.validateAccessCode(accessCode)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<ContactMessageDTO> messages = contactService.getAllContactMessages();
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/dashboard-stats")
    public ResponseEntity<Map<String, Object>> getDashboardStats(@RequestParam String accessCode) {
        if (!securityService.validateAccessCode(accessCode)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Map<String, Object> stats = new HashMap<>();
        stats.put("subscriberCount", newsletterService.getSubscriberCount());
        stats.put("quoteCount", quoteService.getQuoteCount());
        stats.put("messageCount", contactService.getMessageCount());

        return ResponseEntity.ok(stats);
    }
}