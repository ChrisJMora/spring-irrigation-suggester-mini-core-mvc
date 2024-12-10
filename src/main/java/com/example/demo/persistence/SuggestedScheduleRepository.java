package com.example.demo.persistence;

import com.example.demo.model.agriculture.Crop;
import com.example.demo.model.agriculture.Sensor;
import com.example.demo.model.agriculture.SuggestedSchedule;
import com.example.demo.model.agriculture.SuggestedScheduleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuggestedScheduleRepository extends JpaRepository<SuggestedSchedule, Long> {
    List<SuggestedSchedule> findByStatus(SuggestedScheduleStatus status);
    List<SuggestedSchedule> findByStatusAndCrop(SuggestedScheduleStatus status, Crop crop);
}
