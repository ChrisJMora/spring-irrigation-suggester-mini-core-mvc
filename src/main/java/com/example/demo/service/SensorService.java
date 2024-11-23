package com.example.demo.service;

import com.example.demo.exception.EmptyRecordException;
import com.example.demo.exception.EmptyTableException;
import com.example.demo.exception.SaveRecordFailException;
import com.example.demo.model.agriculture.Sensor;
import com.example.demo.model.agriculture.SensorRecord;

import java.util.List;

public interface SensorService {
    /**
     * Get all sensors from database, if the table is empty then throw an
     * exception.
     * @return List of the sensors founded in the database.
     * @exception EmptyTableException When the table have not records.
     */
    List<Sensor> getAllSensors();

    /**
     * Get all records of a sensor from database, if the table is empty then
     * throw an exception.
     * @return List of all the records of a sensor founded in the database.
     * @exception EmptyTableException When the table have not records.
     */
    List<SensorRecord> getAllRecords();

    /**
     * Get the most recent record of a sensor from database, if the record is
     * empty then throw an exception.
     * @param sensor The sensor in which it gets the recent record.
     * @return The recent record of a sensor founded in the database.
     * @exception EmptyRecordException When the record is empty.
     */
    SensorRecord getMostRecentRecord(Sensor sensor);

    /**
     * Create or update a sensor record in the database, if the record is not
     * saved then throw an exception.
     * @param sensorRecord The record that will be saved.
     * @exception SaveRecordFailException When the record couldn't been saved.
     */
    void saveSensorRecord(SensorRecord sensorRecord);
}
