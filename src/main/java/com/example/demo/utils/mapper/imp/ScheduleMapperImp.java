package com.example.demo.utils.mapper.imp;

import com.example.demo.dto.ScheduleDTO;
import com.example.demo.model.agriculture.Crop;
import com.example.demo.model.agriculture.Schedule;
import com.example.demo.service.CropService;
import com.example.demo.utils.mapper.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleMapperImp implements ScheduleMapper {

    @Autowired
    private CropService cropService;

    @Override
    public ScheduleDTO toDto(Schedule schedule) {
        if (schedule == null) return null;
        ScheduleDTO scheduleDTO = new ScheduleDTO();

        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setStatus(schedule.getStatus());
        scheduleDTO.setDate(schedule.getDate());
        scheduleDTO.setCreatedAt(schedule.getCreatedAt());
        scheduleDTO.setUpdatedAt(schedule.getUpdatedAt());
        scheduleDTO.setCropId(schedule.getCrop() != null ? schedule.getCrop().getId() : null);
        scheduleDTO.setCropName(schedule.getCrop() != null ? schedule.getCrop().getName() : null);
        scheduleDTO.setStartTime(schedule.getStartTime());
        scheduleDTO.setEndTime(schedule.getEndTime());

        return scheduleDTO;
    }

    @Override
    public Schedule toEntity(ScheduleDTO scheduleDTO) {
        if (scheduleDTO == null) return null;
        Schedule schedule = new Schedule();

        schedule.setId(scheduleDTO.getId());
        schedule.setStatus(scheduleDTO.getStatus());
        schedule.setDate(scheduleDTO.getDate());
        schedule.setCreatedAt(scheduleDTO.getCreatedAt());
        schedule.setUpdatedAt(scheduleDTO.getUpdatedAt());
        Crop crop = cropService.getCropById(scheduleDTO.getCropId());
        schedule.setCrop(crop);
        schedule.setStartTime(scheduleDTO.getStartTime());
        schedule.setEndTime(scheduleDTO.getEndTime());

        return schedule;
    }

    @Override
    public List<ScheduleDTO> toDtoList(List<Schedule> schedules) {
        if (schedules == null) return null;
        List<ScheduleDTO> list = new ArrayList<>( schedules.size() );
        for ( Schedule schedule : schedules ) {
            list.add( toDto(schedule) );
        }
        return list;
    }

    @Override
    public List<Schedule> toEntityList(List<ScheduleDTO> scheduleDTOs) {
        if (scheduleDTOs == null) return null;
        List<Schedule> list = new ArrayList<>( scheduleDTOs.size() );
        for ( ScheduleDTO scheduleDTO : scheduleDTOs ) {
            list.add( toEntity(scheduleDTO) );
        }
        return list;
    }
}
