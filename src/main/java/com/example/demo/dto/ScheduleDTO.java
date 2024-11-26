package com.example.demo.dto;

import com.example.demo.model.agriculture.ScheduleStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ScheduleDTO {
    private LocalDateTime date;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ScheduleStatus status;
}
