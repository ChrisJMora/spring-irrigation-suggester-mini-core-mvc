package com.example.demo.utils.converterer;

import com.example.demo.model.agriculture.ScheduleStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ScheduleStatusConverter implements AttributeConverter<ScheduleStatus, String> {

    @Override
    public String convertToDatabaseColumn(ScheduleStatus scheduleStatus) {
        return scheduleStatus.getDescription();
    }

    @Override
    public ScheduleStatus convertToEntityAttribute(String dbData) {
        return ScheduleStatus.fromDescription(dbData);
    }
}
