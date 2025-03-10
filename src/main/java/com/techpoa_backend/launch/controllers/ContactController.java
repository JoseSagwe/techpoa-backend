package com.techpoa_backend.launch.controllers;

import com.techpoa_backend.launch.dto.ContactMessageDTO;
import com.techpoa_backend.launch.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/submit")
    public ResponseEntity<Map<String, Object>> submitContactMessage(@RequestBody ContactMessageDTO contactMessageDTO) {
        Map<String, Object> response = new HashMap<>();

        ContactMessageDTO savedMessage = contactService.submitContactMessage(contactMessageDTO);

        response.put("success", true);
        response.put("message", "Thank you for reaching out! We'll get back to you soon.");

        return ResponseEntity.ok(response);
    }
}
