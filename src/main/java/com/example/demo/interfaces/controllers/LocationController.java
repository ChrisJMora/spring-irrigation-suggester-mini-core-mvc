package com.example.demo.interfaces.controllers;

import com.example.demo.domain.models.Location;
import com.example.demo.domain.models.ApiResult;
import com.example.demo.domain.models.Error;
import com.example.demo.domain.models.WrappedEntity;
import com.example.demo.application.ports.services.LocationService;
import com.example.demo.application.ports.mappers.LocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private LocationMapper locationMapper;

    @PreAuthorize("hasRole('ADMINISTRATOR') Or hasRole('SUPERVISOR')")
    @GetMapping("/all")
    public ResponseEntity<ApiResult> getAllLocations() {
        try {
            List<Location> locations = locationService.getAllLocations();
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(new WrappedEntity<>(locationMapper.toDtoList(locations)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(e.getMessage()));
        }
    }
}
