package com.techpoa_backend.launch.service;

import com.techpoa_backend.launch.dto.NewsletterSubscriberDTO;
import com.techpoa_backend.launch.entity.NewsletterSubscriber;
import com.techpoa_backend.launch.repository.NewsletterSubscriberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsletterService {

    private final NewsletterSubscriberRepository subscriberRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public NewsletterService(NewsletterSubscriberRepository subscriberRepository, ModelMapper modelMapper) {
        this.subscriberRepository = subscriberRepository;
        this.modelMapper = modelMapper;
    }

    public boolean subscribeToNewsletter(NewsletterSubscriberDTO subscriberDTO) {
        // Check if email already exists
        if (subscriberRepository.existsByEmail(subscriberDTO.getEmail())) {
            return false; // Already subscribed
        }

        // Create and save new subscriber
        NewsletterSubscriber subscriber = modelMapper.map(subscriberDTO, NewsletterSubscriber.class);
        subscriberRepository.save(subscriber);
        return true;
    }

    public List<NewsletterSubscriberDTO> getAllSubscribers() {
        return subscriberRepository.findAll().stream()
                .map(subscriber -> modelMapper.map(subscriber, NewsletterSubscriberDTO.class))
                .collect(Collectors.toList());
    }

    public long getSubscriberCount() {
        return subscriberRepository.count();
    }
}
