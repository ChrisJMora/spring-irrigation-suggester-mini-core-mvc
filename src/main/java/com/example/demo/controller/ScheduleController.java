package com.example.demo.controller;

import com.example.demo.exception.UserNameOrEmailAreadyExistsException;
import com.example.demo.model.admin.UserEntity;
import com.example.demo.model.agriculture.Schedule;
import com.example.demo.model.httpResponse.ApiResult;
import com.example.demo.model.httpResponse.Error;
import com.example.demo.model.httpResponse.ErrorDetail;
import com.example.demo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PreAuthorize("hasRole('ADMINISTRATOR') Or hasRole('SUPERVISOR')")
    @GetMapping("/all")
    public ResponseEntity<ApiResult> getAllSchedules() {
        try {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(scheduleService.getAllSchedules());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/add")
    public ResponseEntity<ApiResult> addSchedule(@RequestBody Schedule schedule) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(scheduleService.saveSchedule(schedule));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(e.getMessage()));
        }
    }
}
