package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class ForecastDTO {
    private short precipitation;
    private LocalDate date;
    private LocationDTO location;
}
