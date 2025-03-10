package com.techpoa_backend.admin.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AdminSecurityService {
    @Value("${admin.access.code:techpoa@admin2025}")
    private String adminAccessCode;

    public boolean validateAccessCode(String accessCode) {
        return adminAccessCode.equals(accessCode);
    }
}