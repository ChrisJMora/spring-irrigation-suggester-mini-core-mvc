package com.example.demo.controller;

import com.example.demo.model.agriculture.Crop;
import com.example.demo.model.httpResponse.ApiResult;
import com.example.demo.model.httpResponse.Error;
import com.example.demo.model.httpResponse.WrappedEntity;
import com.example.demo.service.CropService;
import com.example.demo.utils.mapper.CropMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/crop")
public class CropController {

    @Autowired
    private CropService cropService;

    @Autowired
    private CropMapper cropMapper;

    @PreAuthorize("hasRole('ADMINISTRATOR') Or hasRole('SUPERVISOR')")
    @GetMapping("/all")
    public ResponseEntity<ApiResult> getAllCrops() {
        try {
            List<Crop> crops = cropService.getAllCrops();
            return ResponseEntity.status(HttpStatus.OK).body(new WrappedEntity<>(cropMapper.toDtoList(crops)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/create")
    public ResponseEntity<ApiResult> createCrop() {
        try {
            List<Crop> crops = cropService.getAllCrops();
            return ResponseEntity.status(HttpStatus.OK).body(new WrappedEntity<>(cropMapper.toDtoList(crops)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e.getMessage()));
        }
    }
}
