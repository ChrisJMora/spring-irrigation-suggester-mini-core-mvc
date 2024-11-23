package com.example.demo.utils.converterer;

import com.example.demo.model.agriculture.SprinklerStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SprinklerStatusConverter implements AttributeConverter<SprinklerStatus, String> {

    @Override
    public String convertToDatabaseColumn(SprinklerStatus sprinklerStatus) {
        return sprinklerStatus.getDescription();
    }

    @Override
    public SprinklerStatus convertToEntityAttribute(String dbData) {
        return SprinklerStatus.fromDescription(dbData);
    }
}
