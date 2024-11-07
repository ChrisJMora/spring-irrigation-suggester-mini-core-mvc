package com.example.demo.model.agriculture;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "soil", schema = "agriculture")
public class Soil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "soil_id")
    private Long id;

    @Column(name = "soil_name")
    private String name;
    @Column(name = "soil_water_retention")
    private float waterRetention;
}
