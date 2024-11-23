package com.example.demo.model.agriculture;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@EqualsAndHashCode
@Entity
@Table(name = "location", schema = "agriculture")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    @Column(name = "location_latitude")
    private float latitude;
    @Column(name = "location_longitude")
    private float longitude;
}
