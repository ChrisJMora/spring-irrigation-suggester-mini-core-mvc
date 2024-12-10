package com.example.demo.utils.mapper;

import com.example.demo.dto.ScheduleDTO;
import com.example.demo.model.agriculture.Schedule;

import java.util.List;

public interface ScheduleMapper {
    ScheduleDTO toDto(Schedule schedule);
    Schedule toEntity(ScheduleDTO scheduleDTO);
    List<ScheduleDTO> toDtoList(List<Schedule> schedules);
    List<Schedule> toEntityList(List<ScheduleDTO> scheduleDTOs);
}
