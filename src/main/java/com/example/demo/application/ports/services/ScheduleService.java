/**
 * Implementation service for managing schedules.
 * This service provides methods to perform CRUD operations on schedules and suggested schedules in the database.
 */
package com.example.demo.application.ports.services;

import com.example.demo.domain.exceptions.*;
import com.example.demo.domain.models.*;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    /**
     * Retrieves all schedules from the database.
     * If the table is empty, an exception is thrown.
     *
     * @return A list of schedules found in the database.
     * @throws EmptyTableException When the schedules table has no records.
     */
    List<Schedule> getAllSchedules();

    /**
     * Retrieves all suggested schedules from the database.
     * If the table is empty, an exception is thrown.
     *
     * @return A list of suggested schedules found in the database.
     * @throws EmptyTableException When the suggested schedules table has no records.
     */
    List<SuggestedSchedule> getAllSuggestedSchedule();

    /**
     * Retrieves a suggested schedule by its ID.
     *
     * @param id The ID of the suggested schedule to retrieve.
     * @return The suggested schedule found.
     * @throws EmptyRecordException When no suggested schedule is found with the provided ID.
     */
    SuggestedSchedule getSuggestedScheduleById(Long id);

    /**
     * Retrieves a schedule by its ID.
     *
     * @param id The ID of the schedule to retrieve.
     * @return The schedule found.
     * @throws EmptyRecordException When no schedule is found with the provided ID.
     */
    Schedule getScheduleById(Long id);

    /**
     * Retrieves all schedules filtered by crop, status, and date.
     * If no schedules match the criteria, an exception is thrown.
     *
     * @param crop The crop to filter schedules by.
     * @param status The status to filter schedules by.
     * @param date The date to filter schedules by.
     * @return A list of schedules matching the criteria.
     * @throws EmptyFilterException When no schedules match the provided filters.
     */
    List<Schedule> getAllScheduleByCropAndStatusAndDate(Crop crop, ScheduleStatus status, LocalDate date);

    /**
     * Filters all suggested schedules by their status and crop.
     * If no schedules match the criteria, an exception is thrown.
     *
     * @param status The status to filter suggested schedules by.
     * @param crop The crop to filter suggested schedules by.
     * @return A list of suggested schedules matching the criteria.
     * @throws EmptyFilterException When no suggested schedules match the provided filters.
     */
    List<SuggestedSchedule> getAllSuggestedScheduleByStatusAndCrop(SuggestedScheduleStatus status, Crop crop);

    /**
     * Filters all suggested schedules by their status.
     * If no schedules match the criteria, an exception is thrown.
     *
     * @param status The status to filter suggested schedules by.
     * @return A list of suggested schedules matching the criteria.
     * @throws EmptyFilterException When no suggested schedules match the provided filter.
     */
    List<SuggestedSchedule> getAllSuggestedScheduleByStatus(SuggestedScheduleStatus status);

    List<Schedule> getAllSchedulesByCrop(Crop crop);

    /**
     * Creates or updates a suggested schedule in the database.
     * If the suggested schedule is not saved successfully, an exception is thrown.
     *
     * @param suggestedSchedule The suggested schedule to be saved.
     * @return The saved suggested schedule.
     * @throws SaveRecordFailException When the suggested schedule could not be saved.
     */
    SuggestedSchedule saveSuggestedSchedule(SuggestedSchedule suggestedSchedule);

    /**
     * Saves a schedule to the database, creating a new record or updating an existing one.
     * If the save operation is unsuccessful,
     * a {@link SaveRecordFailException} is thrown.
     *
     * @param schedule The schedule object to be saved.
     * @return The saved schedule object
     * @throws SaveRecordFailException If the schedule could not be saved to
     * the database.
     */
    Schedule saveSchedule(Schedule schedule);

    /**
     * Adds a new schedule to the system after validating it.
     * If the validation fails, an appropriate exception is thrown.
     *
     * @param schedule The schedule object to be added.
     * @return The saved schedule object.
     * @throws FutureScheduleException If the schedule's start time is in the past.
     * @throws ScheduleConflictException If the schedule conflicts with existing schedules for the same crop.
     * @throws SaveRecordFailException If the schedule could not be saved to the database.
     */
    public Schedule addSchedule(Schedule schedule);

    List<SuggestedSchedule> fetchPendingSuggestedSchedules();

    List<SuggestedSchedule> fetchPendingSuggestedSchedules(Crop crop);

    void cancelSuggestedSchedule(SuggestedSchedule schedule);

    boolean isSuggestedScheduleOutdated(SuggestedSchedule schedule);

    boolean areCropAndForecastInSameLocation(Crop crop, Forecast forecast);
}
