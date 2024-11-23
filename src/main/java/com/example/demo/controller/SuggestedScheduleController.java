package com.example.demo.controller;

import com.example.demo.model.agriculture.SuggestedSchedule;
import com.example.demo.model.httpResponse.ApiResult;
import com.example.demo.model.httpResponse.Error;
import com.example.demo.model.httpResponse.WrappedEntity;
import com.example.demo.service.SuggestedScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/schedule/suggested")
public class SuggestedScheduleController {

    @Autowired
    private SuggestedScheduleService suggestedScheduleService;

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/all")
    public ResponseEntity<ApiResult> getAllSuggestedSchedule() {
        try {
            List<SuggestedSchedule> suggestedSchedules = suggestedScheduleService.getAllSuggestedSchedule();
            return ResponseEntity.status(HttpStatus.FOUND).body(new WrappedEntity<>(suggestedSchedules));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error(e.getMessage()));
        }
    }
}
