package com.example.demo.persistence;

import com.example.demo.model.agriculture.Crop;
import com.example.demo.model.agriculture.Forecast;
import com.example.demo.model.agriculture.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
    Optional<Crop> findByLocation(Location location);
}
