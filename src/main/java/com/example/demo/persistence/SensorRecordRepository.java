package com.example.demo.persistence;

import com.example.demo.model.agriculture.Sensor;
import com.example.demo.model.agriculture.SensorRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorRecordRepository extends JpaRepository<SensorRecord, Long> {
    @Query(value = """
            SELECT *
            FROM agriculture.sensor_record
            WHERE sensor_id = :#{#sensor.getId()}
            ORDER BY sensor_record_date DESC, sensor_record_time DESC
            LIMIT 1
            """, nativeQuery = true)
    Optional<SensorRecord> findMostRecentRecordBySensor(@Param("sensor") Sensor sensor);
}
