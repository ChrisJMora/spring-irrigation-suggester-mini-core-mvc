package com.example.demo.controller;

import com.example.demo.exception.UserNameOrEmailAreadyExistsException;
import com.example.demo.model.admin.UserEntity;
import com.example.demo.model.agriculture.Crop;
import com.example.demo.model.httpResponse.ApiResult;
import com.example.demo.model.httpResponse.Error;
import com.example.demo.model.httpResponse.ErrorDetail;
import com.example.demo.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
                    .body(cropService.getAllCrops());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/add")
    public ResponseEntity<ApiResult> addCrop(@RequestBody Crop crop) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(cropService.saveCrop(crop));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(e.getMessage()));
        }
    }
}
