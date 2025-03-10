package com.techpoa_backend.launch.repository;


import com.techpoa_backend.launch.entity.QuoteRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRequestRepository extends JpaRepository<QuoteRequest, Long> {
    // Add custom query methods if needed
}
