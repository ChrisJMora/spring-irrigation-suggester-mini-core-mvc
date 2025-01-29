package com.example.demo.application.utils;

import com.example.demo.application.dto.SuggestedScheduleDTO;
import com.example.demo.domain.models.Crop;
import com.example.demo.domain.models.SuggestedSchedule;
import com.example.demo.domain.models.SuggestedScheduleStatus;
import com.example.demo.application.ports.services.CropService;
import com.example.demo.application.ports.mappers.SuggestedScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SuggestedScheduleMapperImp implements SuggestedScheduleMapper {

    @Autowired
    private CropService cropService;

    @Override
    public SuggestedScheduleDTO toDTO(SuggestedSchedule schedule) {
        if (schedule == null) return null;
        SuggestedScheduleDTO scheduleDTO = new SuggestedScheduleDTO();
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setDelayTime(schedule.getDelayTime());
        scheduleDTO.setCreatedAt(schedule.getCreatedAt());
        scheduleDTO.setUpdatedAt(schedule.getUpdatedAt());
        scheduleDTO.setStatus(schedule.getStatus().getDescription());
        scheduleDTO.setCropId(schedule.getCrop().getId());
        scheduleDTO.setCropName(schedule.getCrop().getName());
        return scheduleDTO;
    }

    @Override
    public SuggestedSchedule toEntity(SuggestedScheduleDTO scheduleDTO) {
        if (scheduleDTO == null) return null;
        SuggestedSchedule schedule = new SuggestedSchedule();
        schedule.setId(scheduleDTO.getId());
        schedule.setDelayTime(scheduleDTO.getDelayTime());
        schedule.setCreatedAt(scheduleDTO.getCreatedAt());
        schedule.setUpdatedAt(scheduleDTO.getUpdatedAt());
        schedule.setStatus(SuggestedScheduleStatus.fromDescription(scheduleDTO.getStatus()));
        Crop crop = cropService.getCropById(schedule.getId());
        schedule.setCrop(crop);
        return schedule;
    }

    @Override
    public List<SuggestedScheduleDTO> toDtoList(List<SuggestedSchedule> schedules) {
        if ( schedules == null ) {
            return null;
        }
        List<SuggestedScheduleDTO> list = new ArrayList<>( schedules.size() );
        for ( SuggestedSchedule schedule : schedules ) {
            list.add( toDTO(schedule) );
        }
        return list;
    }

    @Override
    public List<SuggestedSchedule> toEntityList(List<SuggestedScheduleDTO> scheduleDTOs) {
        if (scheduleDTOs == null) {
            return null;
        }
        List<SuggestedSchedule> list = new ArrayList<>( scheduleDTOs.size() );
        for ( SuggestedScheduleDTO scheduleDTO : scheduleDTOs ) {
            list.add( toEntity(scheduleDTO) );
        }
        return list;
    }
}
