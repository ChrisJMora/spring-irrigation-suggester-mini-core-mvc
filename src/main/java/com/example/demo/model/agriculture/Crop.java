package com.example.demo.model.agriculture;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

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
    @Column(name = "crop_root_height")
    private float rootHeight;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;
    @OneToOne
    @JoinColumn(name = "soil_id")
    private Soil soil;
    @OneToMany(mappedBy = "crop", cascade = CascadeType.ALL, orphanRemoval =
            true, fetch = FetchType.EAGER)
    private List<Sensor> sensors;
    @OneToMany(mappedBy = "crop", cascade = CascadeType.ALL, orphanRemoval =
            true, fetch = FetchType.LAZY)
    private List<Sprinkler> sprinklers;

    public float getWaterRetainPercentage() {
        return soil.getWaterRetention();
    }
}
