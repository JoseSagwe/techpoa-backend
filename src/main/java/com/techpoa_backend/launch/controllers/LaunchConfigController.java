package com.techpoa_backend.launch.controllers;

import com.techpoa_backend.launch.dto.LaunchConfigDTO;
import com.techpoa_backend.launch.service.LaunchConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/launch")
@CrossOrigin(origins = "*")
public class LaunchConfigController {

    private final LaunchConfigService launchConfigService;

    @Autowired
    public LaunchConfigController(LaunchConfigService launchConfigService) {
        this.launchConfigService = launchConfigService;
    }

    @GetMapping("/date")
    public ResponseEntity<LaunchConfigDTO> getLaunchDate() {
        LaunchConfigDTO launchConfig = launchConfigService.getLaunchDate();
        return ResponseEntity.ok(launchConfig);
    }

    @PostMapping("/update")
    public ResponseEntity<LaunchConfigDTO> updateLaunchDate(@RequestBody LaunchConfigDTO launchConfigDTO) {
        LaunchConfigDTO updatedConfig = launchConfigService.updateLaunchDate(launchConfigDTO);
        return ResponseEntity.ok(updatedConfig);
    }
}
