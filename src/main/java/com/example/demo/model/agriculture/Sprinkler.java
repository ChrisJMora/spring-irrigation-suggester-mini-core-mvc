package com.example.demo.model.agriculture;

import com.example.demo.utils.converterer.IrrigationTypeConverter;
import com.example.demo.utils.converterer.SprinklerStatusConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "sprinkler", schema = "agriculture")
public class Sprinkler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sprinkler_id")
    private Long id;

    @Convert(converter = IrrigationTypeConverter.class)
    @Column(name = "sprinkler_type_irrigation")
    private IrrigationType irrigationType;
    @Column(name = "sprinkler_caudal")
    private float flowRate;
    @Convert(converter = SprinklerStatusConverter.class)
    @Column(name = "sprinkler_status")
    private SprinklerStatus status;

    @ManyToOne
    @JoinColumn(name = "crop_id")
    private Crop crop;
}
