package com.example.demo.controller;

import com.example.demo.dto.AddScheduleRequest;
import com.example.demo.dto.ScheduleDTO;
import com.example.demo.dto.UpdateScheduleRequest;
import com.example.demo.exception.*;
import com.example.demo.model.agriculture.*;
import com.example.demo.model.httpResponse.ApiResult;
import com.example.demo.model.httpResponse.Error;
import com.example.demo.model.httpResponse.WrappedEntity;
import com.example.demo.service.ScheduleService;
import com.example.demo.tasks.ForecastEmulator;
import com.example.demo.tasks.IrrigationScheduleManager;
import com.example.demo.tasks.IrrigationScheduleSuggester;
import com.example.demo.utils.mapper.ScheduleMapper;
import com.example.demo.utils.mapper.SuggestedScheduleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "https://irrigation-suggester-mini-core-service.onrender.com")
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
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new WrappedEntity<>(scheduleMapper.toDtoList(schedules)));
        } catch (EmptyTableException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Error(ex.getMessage()));
        } catch (Exception ex) {
            log.error("An error occurred while retrieving schedules: ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error("An unexpected error occurred."));
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("suggested/all")
    public ResponseEntity<ApiResult> getAllSuggestedSchedule() {
        try {
            List<SuggestedSchedule> suggestedSchedules = scheduleService.getAllSuggestedSchedule();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new WrappedEntity<>(suggestedScheduleMapper.toDtoList(suggestedSchedules)));
        } catch (EmptyTableException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Error(ex.getMessage()));
        } catch (Exception ex) {
            log.error("An error occurred while retrieving suggested schedules: ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error("An unexpected error occurred."));
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
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new WrappedEntity<>(suggestedScheduleMapper.toDTO(saved)));
        } catch (EmptyRecordException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Error(ex.getMessage()));
        } catch (SaveRecordFailException ex) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new Error(ex.getMessage()));
        } catch (Exception ex) {
            log.error("An error occurred while accepting suggested schedule: ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error("An unexpected error occurred."));
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/cancel")
    public ResponseEntity<ApiResult> cancelSchedule(@RequestParam Long id) {
        try {
            Schedule schedule = scheduleService.getScheduleById(id);
            schedule.setStatus(ScheduleStatus.CANCELED);
            schedule.setUpdatedAt(LocalDateTime.now());
            Schedule savedSchedule = scheduleService.saveSchedule(schedule);
            irrigationScheduleManager.manageIrrigationScheduleForAllCrops();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new WrappedEntity<>(scheduleMapper.toDto(savedSchedule)));
        } catch (EmptyRecordException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Error(ex.getMessage()));
        } catch (SaveRecordFailException ex) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new Error(ex.getMessage()));
        } catch (Exception ex) {
            log.error("An error occurred while canceling the schedule: ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error("An unexpected error occurred."));
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/create")
    public ResponseEntity<ApiResult> addSchedule(@RequestBody AddScheduleRequest addScheduleRequest) {
        try {
            Schedule schedule = scheduleMapper.toEntity(addScheduleRequest);
            Schedule addedSchedule = scheduleService.addSchedule(schedule);
            irrigationScheduleManager.manageIrrigationScheduleForCrop(addedSchedule.getCrop());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new WrappedEntity<>(scheduleMapper.toDto(addedSchedule)));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new Error(ex.getMessage()));
        } catch (Exception ex) {
            log.error("An error occurred while adding the schedule: ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error("An unexpected error occurred."));
        }
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PostMapping("/update")
    public ResponseEntity<ApiResult> updateSchedule(@RequestBody UpdateScheduleRequest updateScheduleRequest) {
        try {
            Schedule schedule = scheduleMapper.toEntity(updateScheduleRequest);
            Schedule savedSchedule = scheduleService.saveSchedule(schedule);
            irrigationScheduleManager.manageIrrigationScheduleForCrop(savedSchedule.getCrop());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new WrappedEntity<>(scheduleMapper.toDto(savedSchedule)));
        } catch (SaveRecordFailException ex) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new Error(ex.getMessage()));
        } catch (Exception ex) {
            log.error("An error occurred while updating the schedule: ", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Error("An unexpected error occurred."));
        }
    }
}
