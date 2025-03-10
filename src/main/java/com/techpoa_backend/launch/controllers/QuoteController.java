package com.techpoa_backend.launch.controllers;

import com.techpoa_backend.launch.dto.QuoteRequestDTO;
import com.techpoa_backend.launch.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quotes")
@CrossOrigin(origins = "*")
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping("/submit")
    public ResponseEntity<Map<String, Object>> submitQuoteRequest(@RequestBody QuoteRequestDTO quoteRequestDTO) {
        Map<String, Object> response = new HashMap<>();

        QuoteRequestDTO savedQuote = quoteService.submitQuoteRequest(quoteRequestDTO);

        response.put("success", true);
        response.put("message", "Quote request submitted successfully!");
        response.put("quoteId", savedQuote);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuoteRequestDTO>> getAllQuoteRequests() {
        List<QuoteRequestDTO> quotes = quoteService.getAllQuoteRequests();
        return ResponseEntity.ok(quotes);
    }
}
