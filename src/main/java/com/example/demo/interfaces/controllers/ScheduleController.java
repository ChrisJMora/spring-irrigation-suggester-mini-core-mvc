package com.example.demo.interfaces.controllers;

import com.example.demo.application.dto.AddScheduleRequest;
import com.example.demo.application.dto.UpdateScheduleRequest;
import com.example.demo.domain.models.Schedule;
import com.example.demo.domain.models.ScheduleStatus;
import com.example.demo.domain.models.SuggestedSchedule;
import com.example.demo.domain.models.SuggestedScheduleStatus;
import com.example.demo.domain.exceptions.EmptyRecordException;
import com.example.demo.domain.exceptions.EmptyTableException;
import com.example.demo.domain.exceptions.SaveRecordFailException;
import com.example.demo.domain.models.ApiResult;
import com.example.demo.domain.models.Error;
import com.example.demo.domain.models.WrappedEntity;
import com.example.demo.application.ports.services.ScheduleService;
import com.example.demo.application.usecases.IrrigationScheduleManager;
import com.example.demo.application.ports.mappers.ScheduleMapper;
import com.example.demo.application.ports.mappers.SuggestedScheduleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
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
