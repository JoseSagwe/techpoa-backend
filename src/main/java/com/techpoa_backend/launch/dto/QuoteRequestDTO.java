package com.techpoa_backend.launch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuoteRequestDTO {
    private String name;
    private String email;
    private String phone;
    private String company;
    private String projectType;
    private String budget;
    private String timeline;
    private String description;
}
