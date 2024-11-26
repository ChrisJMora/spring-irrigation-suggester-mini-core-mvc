package com.example.demo.persistence;

import com.example.demo.model.agriculture.Crop;
import com.example.demo.model.agriculture.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    List<Sensor> findByCrop(Crop crop);
}
