package com.example.demo.service.imp;

import com.example.demo.exception.EmptyTableException;
import com.example.demo.exception.SaveRecordFailException;
import com.example.demo.model.agriculture.SuggestedSchedule;
import com.example.demo.persistence.SuggestedScheduleRepository;
import com.example.demo.service.SuggestedScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestedScheduleServiceImp implements SuggestedScheduleService {

    @Autowired
    private SuggestedScheduleRepository suggestedScheduleRepository;

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

    /**
     * Create or update a suggested schedule in the database, if the schedule is
     * not saved then throw an exception.
     * @param suggestedSchedule The suggested schedule that will be saved.
     * @return The saved suggested schedule
     * @exception SaveRecordFailException When the record couldn't been saved.
     */
    @Override
    public SuggestedSchedule saveSuggestedSchedule(SuggestedSchedule suggestedSchedule) {
        SuggestedSchedule savedSuggestedSchedule = suggestedScheduleRepository.save(suggestedSchedule);
        if (savedSuggestedSchedule.getId() == null) throw new SaveRecordFailException(SuggestedSchedule.class);
        return savedSuggestedSchedule;
    }
}
