package com.example.demo.model.agriculture;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "crop", schema = "agriculture")
public class Crop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crop_id")
    private Long id;

    @Column(name = "crop_name")
    private String name;
    @Column(name = "crop_water_required")
    private float waterRequired;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;
    @OneToOne
    @JoinColumn(name = "soil_id")
    private Soil soil;
}
