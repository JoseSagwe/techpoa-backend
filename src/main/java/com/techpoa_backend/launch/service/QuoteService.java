package com.techpoa_backend.launch.service;

import com.techpoa_backend.launch.dto.QuoteRequestDTO;
import com.techpoa_backend.launch.entity.QuoteRequest;
import com.techpoa_backend.launch.repository.QuoteRequestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuoteService {

    private final QuoteRequestRepository quoteRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public QuoteService(QuoteRequestRepository quoteRepository, ModelMapper modelMapper) {
        this.quoteRepository = quoteRepository;
        this.modelMapper = modelMapper;
    }

    public QuoteRequestDTO submitQuoteRequest(QuoteRequestDTO quoteRequestDTO) {
        QuoteRequest quoteRequest = modelMapper.map(quoteRequestDTO, QuoteRequest.class);
        QuoteRequest savedRequest = quoteRepository.save(quoteRequest);
        return modelMapper.map(savedRequest, QuoteRequestDTO.class);
    }

    public List<QuoteRequestDTO> getAllQuoteRequests() {
        return quoteRepository.findAll().stream()
                .map(quoteRequest -> modelMapper.map(quoteRequest, QuoteRequestDTO.class))
                .collect(Collectors.toList());
    }

    public long getQuoteCount() {
        return quoteRepository.count();
    }
}
