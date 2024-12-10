package com.example.demo.service;

import com.example.demo.exception.EmptyFilterException;
import com.example.demo.exception.EmptyTableException;
import com.example.demo.exception.SaveRecordFailException;
import com.example.demo.model.agriculture.*;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    /**
     * Get all schedules from database, if the table is empty then
     * throw an exception.
     * @return List of the schedules founded in the database.
     * @exception EmptyTableException When the table have not records.
     */
    List<Schedule> getAllSchedules();

    /**
     * Get all suggested schedules from database, if the table is empty then
     * throw an exception.
     * @return List of the suggested schedules founded in the database.
     * @exception EmptyTableException When the table have not records.
     */
    List<SuggestedSchedule> getAllSuggestedSchedule();

    SuggestedSchedule getSuggestedScheduleById(Long id);

    Schedule getScheduleById(Long id);

    List<Schedule> getAllScheduleByCropAndStatusAndDate(Crop crop,
                                                        ScheduleStatus status, LocalDate date);

    /**
     * Filter all suggested schedules by its status and crop.
     * @param status Status by which the suggested schedule list is filtered.
     * @param crop Crop by which the suggested schedule list is filtered.
     * @return List of suggested schedule filtered.
     */
    List<SuggestedSchedule> getAllSuggestedScheduleByStatusAndCrop(SuggestedScheduleStatus status, Crop crop);

    /**
     * Filter all suggested schedules by its status.
     * @param status Status by which the suggested schedule list is filtered.
     * @return List of suggested schedule filtered.
     * @exception EmptyFilterException When after applying the filter, the
     * table have not records.
     */
    List<SuggestedSchedule> getAllSuggestedScheduleByStatus(SuggestedScheduleStatus status);

    /**
     * Create or update a suggested schedule in the database, if the schedule is
     * not saved then throw an exception.
     * @param schedule The suggested schedule that will be saved.
     * @return The saved suggested schedule
     * @exception SaveRecordFailException When the record couldn't been saved.
     */
    SuggestedSchedule saveSuggestedSchedule(SuggestedSchedule schedule);

    /**
     * Create or update a schedule in the database, if the schedule is not
     * saved then throw an exception.
     * @param schedule The schedule that will be saved.
     * @return The saved schedule
     * @exception SaveRecordFailException When the record couldn't been saved.
     */
    Schedule saveSchedule(Schedule schedule);
}
