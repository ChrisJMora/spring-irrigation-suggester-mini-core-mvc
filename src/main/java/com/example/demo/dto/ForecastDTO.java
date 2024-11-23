package com.example.demo.dto;

import com.example.demo.model.agriculture.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ForecastDTO {
    private short precipitation;
    private LocalDate date;
    private LocationDTO location;
}
