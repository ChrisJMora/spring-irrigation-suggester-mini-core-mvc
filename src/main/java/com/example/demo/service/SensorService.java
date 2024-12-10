/**
 * Implementation service for managing sensors and their records.
 * This service provides methods to perform CRUD operations on sensors and sensor records in the database.
 */
package com.example.demo.service;

import com.example.demo.exception.EmptyFilterException;
import com.example.demo.exception.EmptyRecordException;
import com.example.demo.exception.EmptyTableException;
import com.example.demo.exception.SaveRecordFailException;
import com.example.demo.model.agriculture.Crop;
import com.example.demo.model.agriculture.Sensor;
import com.example.demo.model.agriculture.SensorRecord;

import java.util.List;

public interface SensorService {
    /**
     * Retrieves all sensors from the database.
     * If the table is empty, an exception is thrown.
     *
     * @return A list of sensors found in the database.
     * @throws EmptyTableException When the sensors table has no records.
     */
    List<Sensor> getAllSensors();

    /**
     * Retrieves all sensors filtered by crop.
     * If no sensors match the criteria, an exception is thrown.
     *
     * @param crop The crop to filter sensors by.
     * @return A list of sensors filtered by the specified crop.
     * @throws EmptyFilterException When no sensors match the provided crop.
     */
    List<Sensor> getAllSensorsByCrop(Crop crop);

    /**
     * Retrieves all sensor records from the database.
     * If the table is empty, an exception is thrown.
     *
     * @return A list of all sensor records found in the database.
     * @throws EmptyTableException When the sensor records table has no records.
     */
    List<SensorRecord> getAllRecords();

    /**
     * Retrieves the most recent record of a specified sensor.
     * If no record is found, an exception is thrown.
     *
     * @param sensor The sensor for which to retrieve the most recent record.
     * @return The most recent record of the specified sensor.
     * @throws EmptyRecordException When no record is found for the specified sensor.
     */
    SensorRecord getMostRecentRecord(Sensor sensor);

    /**
     * Creates or updates a sensor record in the database.
     * If the record is not saved successfully, an exception is thrown.
     *
     * @param sensorRecord The sensor record to be saved.
     * @return The saved sensor record.
     * @throws SaveRecordFailException When the sensor record could not be saved.
     */
    SensorRecord saveSensorRecord(SensorRecord sensorRecord);

    /**
     * Creates or updates a sensor in the database . If the sensor is not saved successfully, an exception is thrown.

     * @param sensor The sensor to be saved.
     * @return The saved sensor.
     * @throws SaveRecordFailException When the sensor could not be saved.
     */
    Sensor saveSensor(Sensor sensor);

    /**
     * Saves a list of sensors in the database.
     * If any sensor in the list is not saved successfully, an exception is thrown.
     *
     * @param sensors The list of sensors to be saved.
     * @return A list of the saved sensors.
     * @throws SaveRecordFailException When any sensor could not be saved.
     */
    List<Sensor> saveSensors(List<Sensor> sensors);

    /**
     * Creates a specified number of sensors associated with a given crop.
     *
     * @param crop The crop to associate with the new sensors.
     * @param quantity The number of sensors to create.
     * @return A list of the newly created sensors.
     */
    List<Sensor> createSensors(Crop crop, int quantity);

    /**
     * Creates a random sensor record for a specified sensor.
     * The record is generated using a normal distribution for humidity.
     *
     * @param sensor The sensor for which to create a random record.
     * @return The created random sensor record.
     */
    SensorRecord createRandomRecord(Sensor sensor);
}
