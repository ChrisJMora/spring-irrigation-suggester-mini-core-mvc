package com.example.demo.service;


import com.example.demo.exception.EmptyTableException;
import com.example.demo.exception.SaveRecordFailException;
import com.example.demo.model.agriculture.SuggestedSchedule;

import java.util.List;

public interface SuggestedScheduleService {
    /**
     * Get all suggested schedules from database, if the table is empty then
     * throw an exception.
     * @return List of the suggested schedules founded in the database.
     * @exception EmptyTableException When the table have not records.
     */
    List<SuggestedSchedule> getAllSuggestedSchedule();

    /**
     * Create or update a suggested schedule in the database, if the schedule is
     * not saved then throw an exception.
     * @param schedule The suggested schedule that will be saved.
     * @return The saved suggested schedule
     * @exception SaveRecordFailException When the record couldn't been saved.
     */
    SuggestedSchedule saveSuggestedSchedule(SuggestedSchedule schedule);
}
