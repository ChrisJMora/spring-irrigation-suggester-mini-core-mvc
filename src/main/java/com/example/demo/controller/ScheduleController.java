package com.example.demo.controller;

import com.example.demo.model.agriculture.Schedule;
import com.example.demo.model.agriculture.SuggestedSchedule;
import com.example.demo.model.httpResponse.ApiResult;
import com.example.demo.model.httpResponse.Error;
import com.example.demo.model.httpResponse.WrappedEntity;
import com.example.demo.service.ScheduleService;
import com.example.demo.utils.mapper.ScheduleMapper;
import com.example.demo.utils.mapper.SuggestedScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    private SuggestedScheduleMapper suggestedScheduleMapper;

    @PreAuthorize("hasRole('ADMINISTRATOR') Or hasRole('SUPERVISOR')")
    @GetMapping("/all")
    public ResponseEntity<ApiResult> getAllSchedules() {
        try {
            List<Schedule> schedules = scheduleService.getAllSchedules();
            return ResponseEntity.status(HttpStatus.FOUND).body(new WrappedEntity<>(scheduleMapper.toDtoList(schedules)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("suggested/all")
    public ResponseEntity<ApiResult> getAllSuggestedSchedule() {
        try {
            List<SuggestedSchedule> suggestedSchedules = scheduleService.getAllSuggestedSchedule();
            return ResponseEntity.status(HttpStatus.FOUND).body(new WrappedEntity<>(suggestedScheduleMapper.toDtoList(suggestedSchedules)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/add")
    public ResponseEntity<ApiResult> addSchedule(@RequestBody Schedule schedule) {
        try {
            Schedule savedSchedule = scheduleService.saveSchedule(schedule);
            return ResponseEntity.status(HttpStatus.CREATED).body(new WrappedEntity<>(scheduleMapper.toDto(savedSchedule)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(e.getMessage()));
        }
    }
}
