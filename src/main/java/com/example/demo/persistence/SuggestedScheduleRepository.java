package com.example.demo.persistence;

import com.example.demo.model.agriculture.SuggestedSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuggestedScheduleRepository extends JpaRepository<SuggestedSchedule, Long> {
}
