package com.example.demo.controller;

import com.example.demo.dto.AddCropRequest;
import com.example.demo.dto.CropDTO;
import com.example.demo.exception.EmptyRecordException;
import com.example.demo.exception.EmptyTableException;
import com.example.demo.exception.SaveRecordFailException;
import com.example.demo.model.agriculture.Crop;
import com.example.demo.model.agriculture.IrrigationType;
import com.example.demo.model.httpResponse.ApiResult;
import com.example.demo.model.httpResponse.Error;
import com.example.demo.model.httpResponse.WrappedEntity;
import com.example.demo.service.*;
import com.example.demo.tasks.IrrigationScheduleManager;
import com.example.demo.utils.mapper.CropMapper;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/crop")
public class CropController {

    @Autowired
    private CropService cropService;
    @Autowired
    private SensorService sensorService;
    @Autowired
    private SprinklerService sprinklerService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private SoilService soilService;
    @Autowired
    private CropMapper cropMapper;
    @Autowired
    private IrrigationScheduleManager irrigationScheduleManager;

    @PreAuthorize("hasRole('ADMINISTRATOR') Or hasRole('SUPERVISOR')")
    @GetMapping("/all")
    public ResponseEntity<ApiResult> getAllCrops() {
        try {
            List<Crop> crops = cropService.getAllCrops();
            return ResponseEntity.ok(new WrappedEntity<>(cropMapper.toDtoList(crops)));
        } catch (EmptyTableException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Error(ex.getMessage()));
        } catch (Exception ex) {
            log.error("An error occurred while retrieving crops: ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error("An unexpected error occurred."));
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResult> updateCrop(@PathVariable Long id,
                                                @Valid @RequestBody CropDTO cropDTO) {
        if (!id.equals(cropDTO.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Error("Invalid crop ID in request body."));
        }
        try {
            Crop existingCrop = cropService.getCropById(id);
            Crop crop = cropMapper.toEntity(cropDTO);
            crop.setSoil(existingCrop.getSoil());
            crop.setLocation(existingCrop.getLocation());
            Crop updatedCrop = cropService.saveCrop(crop);
            irrigationScheduleManager.manageIrrigationScheduleForCrop(updatedCrop);
            return ResponseEntity.ok(new WrappedEntity<>(cropMapper.toDTO(updatedCrop)));
        } catch (EmptyRecordException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Error(ex.getMessage()));
        } catch (SaveRecordFailException ex) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new Error(ex.getMessage()));
        } catch (Exception ex) {
            log.error("An error occurred while updating the crop: ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(" An unexpected error occurred."));
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/create")
    public ResponseEntity<ApiResult> addCrop(@RequestBody AddCropRequest cropRequest) {
        Crop crop = cropMapper.toEntity(cropRequest.getCropData());
        try {
            crop.setLocation(locationService.createRandomLocation());
            crop.setSoil(soilService.getRandomSoil());
        } catch (SaveRecordFailException ex) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new Error("Failed to save new random location: " + ex.getMessage()));
        } catch (EmptyTableException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Error("No soils found in the database: " + ex.getMessage()));
        }
        try {
            IrrigationType irrigationType = IrrigationType.fromDescription(cropRequest.getIrrigationType());
            Crop savedCrop = cropService.saveCrop(crop);
            sensorService.createSensors(crop, cropRequest.getNumberOfSensors());
            sprinklerService.createSprinklers(crop, cropRequest.getNumberOfSprinklers(), irrigationType);
            irrigationScheduleManager.manageIrrigationScheduleForCrop(savedCrop);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new WrappedEntity<>(cropMapper.toDTO(savedCrop)));
        } catch (SaveRecordFailException ex) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new Error(ex.getMessage()));
        } catch (Exception ex) {
            log.error("An error occurred while adding the crop: ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error("An unexpected error occurred."));
        }
    }
}