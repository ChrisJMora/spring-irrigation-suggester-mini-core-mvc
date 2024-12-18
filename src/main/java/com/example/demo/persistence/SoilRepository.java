package com.example.demo.persistence;

import com.example.demo.model.agriculture.Soil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoilRepository extends JpaRepository<Soil, Long> {
}
