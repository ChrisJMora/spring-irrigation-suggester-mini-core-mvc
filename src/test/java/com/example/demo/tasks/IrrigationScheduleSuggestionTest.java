package com.example.demo.tasks;

import com.example.demo.domain.models.Crop;
import com.example.demo.domain.models.SuggestedSchedule;
import com.example.demo.application.ports.services.CropService;
import com.example.demo.application.ports.services.ScheduleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class IrrigationScheduleSuggestionTest {
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private CropService cropService;

    @Test
    public void testSaveSuggestedSchedule() {
        // Recuperar un cultivo (asegúrate de que existan cultivos en la BD para esta prueba)
        Crop crop = cropService.getAllCrops().getFirst();
        // Crear un nuevo SuggestedSchedule
        SuggestedSchedule schedule = new SuggestedSchedule(LocalTime.of(6, 0), crop);
        // Guardar el registro y verificar que se guardó
        SuggestedSchedule saved = scheduleService.saveSuggestedSchedule(schedule);
        assertNotNull(saved.getId(), "El ID del registro guardado no debería ser nulo");
    }
}
