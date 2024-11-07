package com.example.demo.model.agriculture;

import com.example.demo.utils.ScheduleStatusConverter;
import com.example.demo.utils.UserTypeConverter;
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
@Table(name = "schedule", schema = "agriculture")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @Column(name = "schedule_date")
    private LocalDateTime date;
    @Column(name = "schedule_created_at")
    private LocalDateTime createdAt;
    @Column(name = "schedule_updated_at")
    private LocalDateTime updatedAt;
    @Convert(converter = ScheduleStatusConverter.class)
    @Column(name = "schedule_status")
    private ScheduleStatus status;

    @OneToOne
    @JoinColumn(name = "crop_id")
    private Crop crop;
}
