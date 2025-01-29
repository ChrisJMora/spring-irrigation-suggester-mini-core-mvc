package com.example.demo.application.ports.mappers;

import com.example.demo.application.dto.AddScheduleRequest;
import com.example.demo.application.dto.ScheduleDTO;
import com.example.demo.application.dto.UpdateScheduleRequest;
import com.example.demo.domain.models.Schedule;

import java.util.List;

public interface ScheduleMapper {
    ScheduleDTO toDto(Schedule schedule);
    Schedule toEntity(AddScheduleRequest addScheduleRequest);
    Schedule toEntity(UpdateScheduleRequest updateScheduleRequest);
    List<ScheduleDTO> toDtoList(List<Schedule> schedules);
    List<Schedule> toEntityList(List<AddScheduleRequest> requests);
}
