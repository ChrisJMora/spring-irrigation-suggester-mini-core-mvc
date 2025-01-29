package com.example.demo.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
public class SensorRecordDTO {
    private float humidity;
    private LocalDate date;
    private LocalTime time;
}
