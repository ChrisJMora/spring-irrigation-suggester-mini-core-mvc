package com.example.demo.service.imp;

import com.example.demo.exception.EmptyFilterException;
import com.example.demo.exception.EmptyRecordException;
import com.example.demo.exception.EmptyTableException;
import com.example.demo.exception.SaveRecordFailException;
import com.example.demo.model.agriculture.Crop;
import com.example.demo.model.agriculture.Forecast;
import com.example.demo.model.agriculture.Sensor;
import com.example.demo.model.agriculture.SensorRecord;
import com.example.demo.persistence.SensorRecordRepository;
import com.example.demo.persistence.SensorRepository;
import com.example.demo.service.SensorService;
import com.example.demo.utils.statistics.NormalDistributionService;
import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class SensorServiceImp implements SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private SensorRecordRepository sensorRecordRepository;

    @Autowired
    private NormalDistributionService normalDistributionService;

    /**
     * Get all sensors from database, if the table is empty then throw an
     * exception.
     * @return List of the sensors founded in the database.
     * @exception EmptyTableException When the table have not records.
     */
    @Override
    public List<Sensor> getAllSensors() {
        List<Sensor> sensors = sensorRepository.findAll();
        if (sensors.isEmpty()) throw new EmptyTableException(Sensor.class);
        return sensors;
    }

    /**
     * Filter all sensors by crop.
     * @param crop Crop by which the sensor list is filtered.
     * @return List of sensors filtered.
     * @exception EmptyFilterException When after applying the filter, the
     * table have not records.
     */
    @Override
    public List<Sensor> getAllSensorsByCrop(Crop crop) {
        List<Sensor> sensors = sensorRepository.findByCrop(crop);
        if (sensors.isEmpty()) throw new EmptyFilterException(Sensor.class, Crop.class);
        return sensors;
    }

    /**
     * Get all records of a sensor from database, if the table is empty then
     * throw an exception.
     * @return List of all the records of a sensor founded in the database.
     * @exception EmptyTableException When the table have not records.
     */
    @Override
    public List<SensorRecord> getAllRecords() {
        List<SensorRecord> records = sensorRecordRepository.findAll();
        if (records.isEmpty()) throw new EmptyTableException(SensorRecord.class);
        return records;
    }

    /**
     * Get the most recent record of a sensor from database, if the record is
     * empty then throw an exception.
     * @param sensor The sensor in which it gets the recent record.
     * @return The recent record of a sensor founded in the database.
     * @exception EmptyRecordException When the record is empty.
     */
    @Override
    public SensorRecord getMostRecentRecord(Sensor sensor) {
        Optional<SensorRecord> recentRecord = sensorRecordRepository.findMostRecentRecordBySensor(sensor);
        if (recentRecord.isEmpty()) throw new EmptyRecordException(SensorRecord.class);
        return recentRecord.get();
    }

    /**
     * Create or update a sensor record in the database, if the record is not
     * saved then throw an exception.
     * @param sensorRecord The record that will be saved.
     * @exception SaveRecordFailException When the record couldn't been saved.
     */
    @Override
    public SensorRecord saveSensorRecord(SensorRecord sensorRecord) {
        SensorRecord savedRecord = sensorRecordRepository.save(sensorRecord);
        if (savedRecord.getId() == null) throw new SaveRecordFailException(SensorRecord.class);
        return savedRecord;
    }

    @Override
    public SensorRecord createRandomRecord(Sensor sensor) {
        SensorRecord randomRecord = new SensorRecord();
        randomRecord.setHumidity(normalDistributionService.generateNormal(0.6f, 0.15f));
        randomRecord.setDate(LocalDate.now());
        randomRecord.setTime(LocalTime.now());
        randomRecord.setSensor(sensor);
        return saveSensorRecord(randomRecord);
    }
}
