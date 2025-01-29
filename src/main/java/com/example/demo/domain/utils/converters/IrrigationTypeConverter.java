package com.example.demo.domain.utils.converters;

import com.example.demo.domain.models.IrrigationType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class IrrigationTypeConverter implements AttributeConverter<IrrigationType, String> {

    @Override
    public String convertToDatabaseColumn(IrrigationType irrigationType) {
        return irrigationType.getDescription();
    }

    @Override
    public IrrigationType convertToEntityAttribute(String dbData) {
        return IrrigationType.fromDescription(dbData);
    }
}
