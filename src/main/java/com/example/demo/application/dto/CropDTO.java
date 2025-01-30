package com.example.demo.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CropDTO {
    private Long id;

    @NotBlank(message = "The name cant be blank")
    private String name;

    @NotNull(message = "The water required cant be null")
    @Min(value = 0, message = "The water required must be greater than zero")
    private float waterRequired;

    @NotNull(message = "The root height cant be null")
    @Min(value = 0, message = "The root height must be greater than zero")
    private float rootHeight;

    private LocationDTO location;
}
