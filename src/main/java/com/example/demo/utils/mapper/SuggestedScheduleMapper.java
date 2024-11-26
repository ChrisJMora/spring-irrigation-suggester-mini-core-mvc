package com.example.demo.utils.mapper;

import com.example.demo.dto.SuggestedScheduleDTO;
import com.example.demo.model.agriculture.SuggestedSchedule;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SuggestedScheduleMapper {
    SuggestedScheduleDTO toDTO(SuggestedSchedule schedule);
    SuggestedSchedule toEntity(SuggestedScheduleDTO scheduleDTO);
    List<SuggestedScheduleDTO> toDtoList(List<SuggestedSchedule> schedules);
    List<SuggestedSchedule> toEntityList(List<SuggestedScheduleDTO> scheduleDTOs);
}
