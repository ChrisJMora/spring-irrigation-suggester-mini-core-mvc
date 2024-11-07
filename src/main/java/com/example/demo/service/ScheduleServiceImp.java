package com.example.demo.service;

import com.example.demo.model.agriculture.Schedule;
import com.example.demo.model.httpResponse.WrappedEntity;
import com.example.demo.persistence.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImp implements ScheduleService{

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public WrappedEntity<List<Schedule>> getAllSchedules() {
        return new WrappedEntity<>(scheduleRepository.findAll());
    }

    @Override
    public WrappedEntity<Schedule> saveSchedule(Schedule schedule) {
        return new WrappedEntity<>(scheduleRepository.save(schedule));
    }
}
