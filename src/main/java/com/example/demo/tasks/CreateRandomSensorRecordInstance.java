package com.example.demo.tasks;

import com.example.demo.model.agriculture.Sensor;
import com.example.demo.model.agriculture.SensorRecord;
import com.example.demo.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateRandomSensorRecordInstance {

    @Autowired
    private SensorService sensorService;

    @Scheduled(fixedRate = 15000) // Every 15 seconds
    public void CreateInstance() {
        List<Sensor> sensors = sensorService.getAllSensors();
        for (Sensor sensor : sensors) {
            SensorRecord sensorRecord = new SensorRecord(sensor);
            sensorService.saveSensorRecord(sensorRecord);
        }
    }
}
