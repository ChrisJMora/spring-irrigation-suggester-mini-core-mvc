package com.example.demo.interfaces.controllers;

import com.example.demo.domain.exceptions.EmptyTableException;
import com.example.demo.domain.models.SensorRecord;
import com.example.demo.domain.models.ApiResult;
import com.example.demo.domain.models.Error;
import com.example.demo.domain.models.WrappedEntity;
import com.example.demo.application.ports.services.SensorService;
import com.example.demo.application.ports.mappers.SensorRecordMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/sensor")
public class SensorController {

    @Autowired
    private SensorService sensorService;
    @Autowired
    private SensorRecordMapper sensorRecordMapper;

    @PreAuthorize("hasRole('ADMINISTRATOR') Or hasRole('SUPERVISOR')")
    @GetMapping("/all")
    public ResponseEntity<ApiResult> getAllSensors() {
        try {
            List<SensorRecord> sensorRecords = sensorService.getAllRecords();
            return ResponseEntity.status(HttpStatus.OK).body(new WrappedEntity<>(sensorRecordMapper.toDtoList(sensorRecords)));
        } catch (EmptyTableException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Error(ex.getMessage()));
        } catch (Exception ex) {
            log.error("An error occurred while retrieving crops: ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error("An unexpected error occurred."));
        }
    }
}
