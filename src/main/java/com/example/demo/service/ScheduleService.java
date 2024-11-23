package com.example.demo.service;

import com.example.demo.exception.EmptyTableException;
import com.example.demo.exception.SaveRecordFailException;
import com.example.demo.model.agriculture.Schedule;

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
     * Create or update a schedule in the database, if the schedule is not
     * saved then throw an exception.
     * @param schedule The schedule that will be saved.
     * @return The saved schedule
     * @exception SaveRecordFailException When the record couldn't been saved.
     */
    Schedule saveSchedule(Schedule schedule);
}
