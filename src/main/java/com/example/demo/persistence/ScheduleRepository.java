package com.example.demo.persistence;

import com.example.demo.model.agriculture.Crop;
import com.example.demo.model.agriculture.Schedule;
import com.example.demo.model.agriculture.ScheduleStatus;
import com.example.demo.model.agriculture.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByCropAndStatusAndDate(Crop crop,
                                              ScheduleStatus status,
                                              LocalDate date);
    List<Schedule> findByCrop(Crop crop);
}
