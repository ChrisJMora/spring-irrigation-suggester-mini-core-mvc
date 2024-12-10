package com.example.demo.service.imp;

import com.example.demo.exception.EmptyFilterException;
import com.example.demo.exception.EmptyRecordException;
import com.example.demo.exception.EmptyTableException;
import com.example.demo.exception.SaveRecordFailException;
import com.example.demo.model.agriculture.*;
import com.example.demo.model.httpResponse.WrappedEntity;
import com.example.demo.persistence.ScheduleRepository;
import com.example.demo.persistence.SuggestedScheduleRepository;
import com.example.demo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImp implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private SuggestedScheduleRepository suggestedScheduleRepository;

    /**
     * Get all schedules from database, if the table is empty then
     * throw an exception.
     * @return List of the schedules founded in the database.
     * @exception EmptyTableException When the table have not records.
     */
    @Override
    public List<Schedule> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        if(schedules.isEmpty()) throw new EmptyTableException(Schedule.class);
        return schedules;
    }

    /**
     * Get all suggested schedules from database, if the table is empty then
     * throw an exception.
     * @return List of the suggested schedules founded in the database.
     * @exception EmptyTableException When the table have not records.
     */
    @Override
    public List<SuggestedSchedule> getAllSuggestedSchedule() {
        List<SuggestedSchedule> suggestedSchedules = suggestedScheduleRepository.findAll();
        if (suggestedSchedules.isEmpty()) throw new EmptyTableException(SuggestedSchedule.class);
        return suggestedSchedules;
    }

    @Override
    public SuggestedSchedule getSuggestedScheduleById(Long id) {
        Optional<SuggestedSchedule> schedule = suggestedScheduleRepository.findById(id);
        if (schedule.isEmpty()) throw new EmptyRecordException(SuggestedSchedule.class);
        return schedule.get();
    }

    @Override
    public Schedule getScheduleById(Long id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        if (schedule.isEmpty()) throw new EmptyRecordException(SuggestedSchedule.class);
        return schedule.get();
    }

    @Override
    public List<Schedule> getAllScheduleByCropAndStatusAndDate(Crop crop,
                                                               ScheduleStatus status, LocalDate date) {
        List<Schedule> schedules =
                scheduleRepository.findByCropAndStatusAndDate(crop, status, date);
        if (schedules.isEmpty()) throw new EmptyFilterException(Schedule.class, Crop.class, ScheduleStatus.class, LocalDate.class);
        return schedules;
    }

    /**
     * Filter all suggested schedules by its status and crop.
     * @param status Status by which the suggested schedule list is filtered.
     * @param crop Crop by which the suggested schedule list is filtered.
     * @return List of suggested schedule filtered.
     * @exception EmptyFilterException When after applying the filter, the
     * table have not records.
     */
    @Override
    public List<SuggestedSchedule> getAllSuggestedScheduleByStatusAndCrop(SuggestedScheduleStatus status, Crop crop) {
        List<SuggestedSchedule> schedules =
                suggestedScheduleRepository.findByStatusAndCrop(status, crop);
        if (schedules.isEmpty()) throw new EmptyFilterException(SuggestedSchedule.class, SuggestedScheduleStatus.class, Crop.class);
        return schedules;
    }

    /**
     * Filter all suggested schedules by its status.
     * @param status Status by which the suggested schedule list is filtered.
     * @return List of suggested schedule filtered.
     * @exception EmptyFilterException When after applying the filter, the
     * table have not records.
     */
    @Override
    public List<SuggestedSchedule> getAllSuggestedScheduleByStatus(SuggestedScheduleStatus status) {
        List<SuggestedSchedule> schedules = suggestedScheduleRepository.findByStatus(status);
        if (schedules.isEmpty()) throw new EmptyFilterException(SuggestedSchedule.class, SuggestedScheduleStatus.class);
        return schedules;
    }

    /**
     * Create or update a suggested schedule in the database, if the schedule is
     * not saved then throw an exception.
     * @param suggestedSchedule The suggested schedule that will be saved.
     * @return The saved suggested schedule
     * @exception SaveRecordFailException When the record couldn't been saved.
     */
    @Override
    public SuggestedSchedule saveSuggestedSchedule(SuggestedSchedule suggestedSchedule) {
        suggestedSchedule.setUpdatedAt(LocalDateTime.now());
        SuggestedSchedule savedSuggestedSchedule = suggestedScheduleRepository.save(suggestedSchedule);
        if (savedSuggestedSchedule.getId() == null) throw new SaveRecordFailException(SuggestedSchedule.class);
        return savedSuggestedSchedule;
    }

    /**
     * Create or update a schedule in the database, if the schedule is not
     * saved then throw an exception.
     * @param schedule The schedule that will be saved.
     * @return The saved schedule
     * @exception SaveRecordFailException When the record couldn't been saved.
     */
    @Override
    public Schedule saveSchedule(Schedule schedule) {
        Schedule savedSchedule = scheduleRepository.save(schedule);
        if (savedSchedule.getId() == null) throw new SaveRecordFailException(Schedule.class);
        return savedSchedule;
    }
}
