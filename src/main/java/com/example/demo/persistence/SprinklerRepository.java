package com.example.demo.persistence;

import com.example.demo.model.agriculture.Sprinkler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprinklerRepository extends JpaRepository<Sprinkler, Long> {
}
