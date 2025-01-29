package com.example.demo.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class ForecastDTO {
    private float probability;
    private short precipitation;
    private LocalDate date;
    private LocationDTO location;
}
