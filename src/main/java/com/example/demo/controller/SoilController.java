package com.example.demo.controller;

import com.example.demo.model.agriculture.Soil;
import com.example.demo.model.httpResponse.ApiResult;
import com.example.demo.model.httpResponse.Error;
import com.example.demo.model.httpResponse.WrappedEntity;
import com.example.demo.service.SoilService;
import com.example.demo.utils.mapper.SoilMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "https://irrigation-suggester-mini-core-service.onrender.com")
@RestController
@RequestMapping("/api/soil")
public class SoilController {

    @Autowired
    private SoilService soilService;

    @Autowired
    private SoilMapper soilMapper;

    @PreAuthorize("hasRole('ADMINISTRATOR') Or hasRole('SUPERVISOR')")
    @GetMapping("/all")
    public ResponseEntity<ApiResult> getAllSoils() {
        try {
            List<Soil> soils = soilService.getAllSoils();
            return ResponseEntity.status(HttpStatus.FOUND).body(new WrappedEntity<>(soilMapper.toDtoList(soils)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e.getMessage()));
        }
    }
}
