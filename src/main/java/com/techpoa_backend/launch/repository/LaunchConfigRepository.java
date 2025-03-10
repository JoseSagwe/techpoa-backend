package com.techpoa_backend.launch.repository;

import com.techpoa_backend.launch.entity.LaunchConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaunchConfigRepository extends JpaRepository<LaunchConfig, Long> {
    // Return the most recently updated configuration
    LaunchConfig findTopByOrderByLastUpdatedDesc();
}
