package com.example.demo.service;

import com.example.demo.model.agriculture.Crop;
import com.example.demo.model.agriculture.Schedule;
import com.example.demo.model.httpResponse.WrappedEntity;

import java.util.List;

public interface ScheduleService {
    WrappedEntity<List<Schedule>> getAllSchedules();
    WrappedEntity<Schedule> saveSchedule(Schedule schedule);
}
