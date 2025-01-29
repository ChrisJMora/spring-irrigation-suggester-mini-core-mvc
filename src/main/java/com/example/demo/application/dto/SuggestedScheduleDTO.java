package com.example.demo.application.dto;

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
    private String status;
    private Long cropId;
    private String cropName;
}