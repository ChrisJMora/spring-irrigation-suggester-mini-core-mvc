package com.example.demo.model.agriculture;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "sensor", schema = "agriculture")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sensor_id")
    private Long id;

    @Column(name = "sensor_humidity")
    private float humidity;
    @Column(name = "sensor_log")
    private LocalDateTime log;

    @OneToOne
    @JoinColumn(name = "crop_id")
    private Crop crop;
}
