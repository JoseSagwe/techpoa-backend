package com.techpoa_backend.launch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactMessageDTO {
    private Long id;
    private String name;
    private String email;
    private String message;
    private String createdAt;
}
