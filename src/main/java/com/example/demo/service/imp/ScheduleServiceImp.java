package com.example.demo.service.imp;

import com.example.demo.exception.EmptyTableException;
import com.example.demo.exception.SaveRecordFailException;
import com.example.demo.model.agriculture.Schedule;
import com.example.demo.model.agriculture.SuggestedSchedule;
import com.example.demo.model.httpResponse.WrappedEntity;
import com.example.demo.persistence.ScheduleRepository;
import com.example.demo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImp implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

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
