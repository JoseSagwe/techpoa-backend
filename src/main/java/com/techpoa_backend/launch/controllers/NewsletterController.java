package com.techpoa_backend.launch.controllers;

import com.techpoa_backend.launch.dto.NewsletterSubscriberDTO;
import com.techpoa_backend.launch.service.NewsletterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/newsletter")
@CrossOrigin(origins = "*")
public class NewsletterController {

    private final NewsletterService newsletterService;

    @Autowired
    public NewsletterController(NewsletterService newsletterService) {
        this.newsletterService = newsletterService;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<Map<String, Object>> subscribeToNewsletter(@RequestBody NewsletterSubscriberDTO subscriberDTO) {
        Map<String, Object> response = new HashMap<>();

        boolean subscribed = newsletterService.subscribeToNewsletter(subscriberDTO);

        if (subscribed) {
            response.put("success", true);
            response.put("message", "Successfully subscribed to newsletter!");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "Email already subscribed to newsletter.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/subscribers")
    public ResponseEntity<List<NewsletterSubscriberDTO>> getAllSubscribers() {
        List<NewsletterSubscriberDTO> subscribers = newsletterService.getAllSubscribers();
        return ResponseEntity.ok(subscribers);
    }
}
