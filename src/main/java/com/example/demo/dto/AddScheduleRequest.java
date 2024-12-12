package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddScheduleRequest {
    private LocalTime startTime;
    private LocalTime endTime;
    private Long cropId;
}
