package com.example.demo.application.ports.mappers;

import com.example.demo.application.dto.SuggestedScheduleDTO;
import com.example.demo.domain.models.SuggestedSchedule;
import java.util.List;

public interface SuggestedScheduleMapper {
    SuggestedScheduleDTO toDTO(SuggestedSchedule schedule);
    SuggestedSchedule toEntity(SuggestedScheduleDTO scheduleDTO);
    List<SuggestedScheduleDTO> toDtoList(List<SuggestedSchedule> schedules);
    List<SuggestedSchedule> toEntityList(List<SuggestedScheduleDTO> scheduleDTOs);
}
