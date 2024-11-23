package com.example.demo.tasks;

import com.example.demo.model.agriculture.Crop;
import com.example.demo.model.agriculture.SuggestedSchedule;
import com.example.demo.service.CropService;
import com.example.demo.service.SuggestedScheduleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ScheduleSuggestionTest {
    @Autowired
    private SuggestedScheduleService suggestedScheduleService;

    @Autowired
    private CropService cropService;

    @Test
    public void testSaveSuggestedSchedule() {
        // Recuperar un cultivo (asegúrate de que existan cultivos en la BD para esta prueba)
        Crop crop = cropService.getAllCrops().get(0);
        // Crear un nuevo SuggestedSchedule
        SuggestedSchedule schedule = new SuggestedSchedule(LocalTime.of(6, 0), crop);
        // Guardar el registro y verificar que se guardó
        SuggestedSchedule saved = suggestedScheduleService.saveSuggestedSchedule(schedule);
        assertNotNull(saved.getId(), "El ID del registro guardado no debería ser nulo");
    }
}
