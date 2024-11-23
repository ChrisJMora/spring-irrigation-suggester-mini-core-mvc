package com.example.demo.utils.converterer;

import com.example.demo.model.agriculture.SuggestedScheduleStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SuggestedScheduleStatusConverter implements AttributeConverter<SuggestedScheduleStatus, String> {

    @Override
    public String convertToDatabaseColumn(SuggestedScheduleStatus suggestedScheduleStatus) {
        return suggestedScheduleStatus.getDescription();
    }

    @Override
    public SuggestedScheduleStatus convertToEntityAttribute(String dbData) {
        return SuggestedScheduleStatus.fromDescription(dbData);
    }
}
