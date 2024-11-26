package com.example.demo.controller;

import com.example.demo.model.agriculture.Sprinkler;
import com.example.demo.model.httpResponse.ApiResult;
import com.example.demo.model.httpResponse.Error;
import com.example.demo.model.httpResponse.WrappedEntity;
import com.example.demo.service.SprinklerService;
import com.example.demo.utils.mapper.SprinklerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e.getMessage()));
        }
    }
}