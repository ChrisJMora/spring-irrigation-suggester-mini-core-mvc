package com.example.demo.interfaces.controllers;

import com.example.demo.domain.models.IrrigationType;
import com.example.demo.domain.models.Sprinkler;
import com.example.demo.domain.models.ApiResult;
import com.example.demo.domain.models.Error;
import com.example.demo.domain.models.WrappedEntity;
import com.example.demo.application.ports.services.SprinklerService;
import com.example.demo.application.ports.mappers.SprinklerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sprinkler")
public class SprinklerController {

    @Autowired
    private SprinklerService sprinklerService;

    @Autowired
    private SprinklerMapper sprinklerMapper;

    @PreAuthorize("hasRole('ADMINISTRATOR') Or hasRole('SUPERVISOR')")
    @GetMapping("/all")
    public ResponseEntity<ApiResult> getAllSprinklers() {
        try {
            List<Sprinkler> sprinklers = sprinklerService.getAllSprinklers();
            return ResponseEntity.status(HttpStatus.FOUND).body(new WrappedEntity<>(sprinklerMapper.toDtoList(sprinklers)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATOR') Or hasRole('SUPERVISOR')")
    @GetMapping("/irrigation/types")
    public ResponseEntity<ApiResult> getIrrigationTypes() {
        List<String> irrigationTypes = Arrays.stream(IrrigationType.values())
                .map(IrrigationType::getDescription)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new WrappedEntity<>(irrigationTypes));
    }
}
