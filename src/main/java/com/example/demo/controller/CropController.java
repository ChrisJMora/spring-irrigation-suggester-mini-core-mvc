package com.example.demo.controller;

import com.example.demo.model.agriculture.Crop;
import com.example.demo.model.httpResponse.ApiResult;
import com.example.demo.model.httpResponse.Error;
import com.example.demo.model.httpResponse.WrappedEntity;
import com.example.demo.service.CropService;
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

    @PreAuthorize("hasRole('ADMINISTRATOR') Or hasRole('SUPERVISOR')")
    @GetMapping("/all")
    public ResponseEntity<ApiResult> getAllCrops() {
        try {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(new WrappedEntity<>(cropService.getAllCrops()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/add/test")
    public ResponseEntity<ApiResult> addCrop() {
        try {
            Crop duplicate = cropService.getAllCrops().getFirst();
            Crop cropTest = new Crop();
            cropTest.setName("NUEVO CULTIVO");
            cropTest.setWaterRequired(100);
            cropTest.setLocation(duplicate.getLocation());
            cropTest.setSoil(duplicate.getSoil());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(cropService.saveCrop(cropTest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(e.getMessage()));
        }
    }
}
