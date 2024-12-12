package com.example.demo.utils.mapper.imp;

import com.example.demo.dto.AddScheduleRequest;
import com.example.demo.dto.ScheduleDTO;
import com.example.demo.dto.UpdateScheduleRequest;
import com.example.demo.model.agriculture.Crop;
import com.example.demo.model.agriculture.Schedule;
import com.example.demo.model.agriculture.ScheduleStatus;
import com.example.demo.service.CropService;
import com.example.demo.service.ScheduleService;
import com.example.demo.utils.mapper.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleMapperImp implements ScheduleMapper {

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private CropService cropService;

    @Override
    public ScheduleDTO toDto(Schedule schedule) {
        if (schedule == null) return null;
        ScheduleDTO scheduleDTO = new ScheduleDTO();

        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setStatus(schedule.getStatus().getDescription());
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
    public Schedule toEntity(AddScheduleRequest addScheduleRequest) {
        if (addScheduleRequest == null) return null;
        Schedule schedule = new Schedule();

        schedule.setDate(LocalDate.now());
        schedule.setStatus(ScheduleStatus.PENDING);
        schedule.setStartTime(addScheduleRequest.getStartTime());
        schedule.setEndTime(addScheduleRequest.getEndTime());
        Crop crop = cropService.getCropById(addScheduleRequest.getCropId());
        schedule.setCrop(crop);
        schedule.setCreatedAt(LocalDateTime.now());
        schedule.setUpdatedAt(LocalDateTime.now());

        return schedule;
    }

    @Override
    public Schedule toEntity(UpdateScheduleRequest updateScheduleRequest) {
        if (updateScheduleRequest == null) return null;
        Schedule schedule = new Schedule();

        schedule.setId(updateScheduleRequest.getId());
        schedule.setDate(LocalDate.now());
        schedule.setStatus(ScheduleStatus.PENDING);
        schedule.setStartTime(updateScheduleRequest.getStartTime());
        schedule.setEndTime(updateScheduleRequest.getEndTime());

        Schedule existingSchedule =
                scheduleService.getScheduleById(updateScheduleRequest.getId());

        schedule.setCrop(existingSchedule.getCrop());
        schedule.setCreatedAt(existingSchedule.getCreatedAt());
        schedule.setUpdatedAt(LocalDateTime.now());

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
    public List<Schedule> toEntityList(List<AddScheduleRequest> requests) {
        if (requests == null) return null;
        List<Schedule> list = new ArrayList<>( requests.size() );
        for ( AddScheduleRequest request : requests ) {
            list.add( toEntity(request) );
        }
        return list;
    }
}
