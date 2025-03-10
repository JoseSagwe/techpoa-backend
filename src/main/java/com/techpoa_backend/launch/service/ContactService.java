package com.techpoa_backend.launch.service;

import com.techpoa_backend.launch.dto.ContactMessageDTO;
import com.techpoa_backend.launch.entity.ContactMessage;
import com.techpoa_backend.launch.repository.ContactMessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {
    private final ContactMessageRepository contactRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ContactService(ContactMessageRepository contactRepository, ModelMapper modelMapper) {
        this.contactRepository = contactRepository;
        this.modelMapper = modelMapper;
    }

    public ContactMessageDTO submitContactMessage(ContactMessageDTO contactMessageDTO) {
        ContactMessage contactMessage = modelMapper.map(contactMessageDTO, ContactMessage.class);
        ContactMessage savedMessage = contactRepository.save(contactMessage);
        return modelMapper.map(savedMessage, ContactMessageDTO.class);
    }

    public List<ContactMessageDTO> getAllContactMessages() {
        return contactRepository.findAll().stream()
                .map(message -> modelMapper.map(message, ContactMessageDTO.class))
                .collect(Collectors.toList());
    }

    public long getMessageCount() {
        return contactRepository.count();
    }
}