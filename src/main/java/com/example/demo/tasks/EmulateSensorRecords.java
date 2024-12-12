package com.example.demo.tasks;

import com.example.demo.exception.EmptyTableException;
import com.example.demo.exception.SaveRecordFailException;
import com.example.demo.model.agriculture.Sensor;
import com.example.demo.model.agriculture.SensorRecord;
import com.example.demo.service.SensorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class EmulateSensorRecords {

    @Autowired
    private SensorService sensorService;

//    @Scheduled(cron = "0 0/1 * * * ?") // Every minute
    @Scheduled(cron = "0 0 */4 * * ?") // Every 4 hours
    public void CreateRecordsForAllSensors() {
        try {
            // Get all sensors, if the list is empty throw EmptyTableException
            List<Sensor> sensors = sensorService.getAllSensors();
            for (Sensor sensor : sensors) {
                // Create and save a new sensor record using normal
                // distribution for humidity
                SensorRecord randomRecord = sensorService.createRandomRecord(sensor);
                log.info("Sensor {}: Humidity: {} %",
                        sensor.getId(),
                        randomRecord.getHumidity() * 100);
            }
        } catch (EmptyTableException ex) {
            log.error("The table of sensors is empty.\n", ex);
        } catch (SaveRecordFailException ex) {
            log.error("There was a problem saving a sensor record.\n", ex);
        }
    }

}
