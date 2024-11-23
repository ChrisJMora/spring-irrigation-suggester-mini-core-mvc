package com.example.demo.model.agriculture;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "sensor_record", schema = "agriculture")
public class SensorRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sensor_record_id")
    private Long id;

    @Column(name = "sensor_record_humidity")
    private float humidity;
    @Column(name = "sensor_record_date")
    private LocalDate date;
    @Column(name = "sensor_record_time")
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "sensor_id", nullable = false)
    private Sensor sensor;

    public SensorRecord(Sensor sensor) {
        Random random = new Random();
        humidity = random.nextFloat(0.1f,0.9f);
        date = LocalDate.now(); time = LocalTime.now();
        this.sensor = sensor;
    }
}
