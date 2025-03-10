package com.techpoa_backend.launch.service;

import com.techpoa_backend.launch.dto.LaunchConfigDTO;
import com.techpoa_backend.launch.entity.LaunchConfig;
import com.techpoa_backend.launch.repository.LaunchConfigRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LaunchConfigService {

    private final LaunchConfigRepository launchConfigRepository;
    private final ModelMapper modelMapper;

    // Cache the launch date for at least 1 hour
    private LaunchConfigDTO cachedConfig = null;
    private LocalDateTime cacheTime = null;

    @Autowired
    public LaunchConfigService(LaunchConfigRepository launchConfigRepository, ModelMapper modelMapper) {
        this.launchConfigRepository = launchConfigRepository;
        this.modelMapper = modelMapper;
    }

    public LaunchConfigDTO getLaunchDate() {
        // Check if cache is valid (not null and less than 1 hour old)
        if (cachedConfig != null && cacheTime != null &&
                LocalDateTime.now().minusHours(1).isBefore(cacheTime)) {
            return cachedConfig;
        }

        LaunchConfig config = launchConfigRepository.findTopByOrderByLastUpdatedDesc();

        // If no config exists, create default with launch date 90 days from now
        if (config == null) {
            config = new LaunchConfig();
            config.setLaunchDate(LocalDateTime.now().plusDays(90));
            config = launchConfigRepository.save(config);
        }

        // Update cache
        cachedConfig = modelMapper.map(config, LaunchConfigDTO.class);
        cacheTime = LocalDateTime.now();

        return cachedConfig;
    }

    public LaunchConfigDTO updateLaunchDate(LaunchConfigDTO launchConfigDTO) {
        LaunchConfig config = launchConfigRepository.findTopByOrderByLastUpdatedDesc();

        if (config == null) {
            config = new LaunchConfig();
        }

        config.setLaunchDate(launchConfigDTO.getLaunchDate());
        LaunchConfig savedConfig = launchConfigRepository.save(config);

        // Update cache after changes
        cachedConfig = modelMapper.map(savedConfig, LaunchConfigDTO.class);
        cacheTime = LocalDateTime.now();

        return cachedConfig;
    }
}