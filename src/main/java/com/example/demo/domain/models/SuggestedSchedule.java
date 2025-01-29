package com.example.demo.domain.models;

import com.example.demo.domain.utils.converters.SuggestedScheduleStatusConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "suggested_schedule", schema = "agriculture")
public class SuggestedSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @Column(name = "schedule_delay_time")
    private LocalTime delayTime;
    @Column(name = "schedule_created_at")
    private LocalDateTime createdAt;
    @Column(name = "schedule_updated_at")
    private LocalDateTime updatedAt;
    @Convert(converter = SuggestedScheduleStatusConverter.class)
    @Column(name = "schedule_status")
    private SuggestedScheduleStatus status;

    @OneToOne
    @JoinColumn(name = "crop_id")
    private Crop crop;

    public SuggestedSchedule(LocalTime irrigationTime, Crop crop) {
        delayTime = irrigationTime;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        status = SuggestedScheduleStatus.PENDING;
        this.crop = crop;
    }
}

