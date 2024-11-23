package com.example.demo.utils.converterer;

import com.example.demo.model.agriculture.IrrigationType;
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
