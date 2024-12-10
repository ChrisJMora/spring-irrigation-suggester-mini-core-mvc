package com.example.demo.dto;

import com.example.demo.model.agriculture.SuggestedScheduleStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter @Setter
public class SuggestedScheduleDTO {
    private Long id;
    private LocalTime delayTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private SuggestedScheduleStatus status;
    private Long cropId;
    private String cropName;
}