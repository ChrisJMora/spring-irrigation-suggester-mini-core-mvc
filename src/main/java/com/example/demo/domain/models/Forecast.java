package com.example.demo.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "forecast", schema = "agriculture")
public class Forecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "forecast_id")
    private Long id;

    @Column(name = "forecast_probability")
    private float probability;
    @Column(name = "forecast_precipitation")
    private short precipitation;
    @Column(name = "forecast_date")
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    public Forecast(Location location) {
        precipitation = (short) ThreadLocalRandom.current().nextInt(1, 5);
        date = LocalDate.now();
        this.location = location;
    }

    public void setRandomPrecipitation() {
        Random random = new Random();
        precipitation = (short) random.nextInt(1, 5);
    }
}

