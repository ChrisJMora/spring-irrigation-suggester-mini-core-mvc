package com.example.demo.controller;

import com.example.demo.dto.ScheduleDTO;
import com.example.demo.model.agriculture.Schedule;
import com.example.demo.model.agriculture.ScheduleStatus;
import com.example.demo.model.agriculture.SuggestedSchedule;
import com.example.demo.model.agriculture.SuggestedScheduleStatus;
import com.example.demo.model.httpResponse.ApiResult;
import com.example.demo.model.httpResponse.Error;
import com.example.demo.model.httpResponse.WrappedEntity;
import com.example.demo.service.ScheduleService;
import com.example.demo.tasks.ForecastEmulator;
import com.example.demo.tasks.IrrigationScheduleManager;
import com.example.demo.tasks.IrrigationScheduleSuggester;
import com.example.demo.utils.mapper.ScheduleMapper;
import com.example.demo.utils.mapper.SuggestedScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Autowired
    private IrrigationScheduleManager irrigationScheduleManager;


    @PreAuthorize("hasRole('ADMINISTRATOR') Or hasRole('SUPERVISOR')")
    @GetMapping("/all")
    public ResponseEntity<ApiResult> getAllSchedules() {
        try {
            List<Schedule> schedules = scheduleService.getAllSchedules();
            return ResponseEntity.status(HttpStatus.OK).body(new WrappedEntity<>(scheduleMapper.toDtoList(schedules)));
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
            return ResponseEntity.status(HttpStatus.OK).body(new WrappedEntity<>(suggestedScheduleMapper.toDtoList(suggestedSchedules)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/suggested/accept")
    public ResponseEntity<ApiResult> acceptSuggestedSchedule(@RequestParam Long id) {
        try {
            SuggestedSchedule schedule = scheduleService.getSuggestedScheduleById(id);
            schedule.setStatus(SuggestedScheduleStatus.ACCEPTED);
            schedule.setUpdatedAt(LocalDateTime.now());
            SuggestedSchedule saved = scheduleService.saveSuggestedSchedule(schedule);
            return ResponseEntity.status(HttpStatus.OK).body(new WrappedEntity<>(suggestedScheduleMapper.toDTO(saved)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/cancel")
    public ResponseEntity<ApiResult> cancelSchedule(@RequestParam Long id) {
        try {
            Schedule schedule = scheduleService.getScheduleById(id);
            schedule.setStatus(ScheduleStatus.CANCELED);
            schedule.setUpdatedAt(LocalDateTime.now());
            Schedule saved = scheduleService.saveSchedule(schedule);
            irrigationScheduleManager.manageIrrigationScheduleForAllCrops();
            return ResponseEntity.status(HttpStatus.OK).body(new WrappedEntity<>(scheduleMapper.toDto(saved)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(e.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/add")
    public ResponseEntity<ApiResult> addSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        try {
            Schedule schedule = scheduleMapper.toEntity(scheduleDTO);
            schedule.setDate(LocalDate.now());
            Schedule saved = scheduleService.saveSchedule(schedule);
            return ResponseEntity.status(HttpStatus.CREATED).body(new WrappedEntity<>(scheduleMapper.toDto(saved)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error(e.getMessage()));
        }
    }
}
