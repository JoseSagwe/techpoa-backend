package com.techpoa_backend.launch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "launch_config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaunchConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "launch_date", nullable = false)
    private LocalDateTime launchDate;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @PrePersist
    @PreUpdate
    public void prePersistAndUpdate() {
        lastUpdated = LocalDateTime.now();
    }
}
