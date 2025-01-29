package com.example.demo.domain.utils.converters;

import com.example.demo.domain.models.UserType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserTypeConverter implements AttributeConverter<UserType, String> {

    @Override
    public String convertToDatabaseColumn(UserType userType) {
        return userType.getDescription();
    }

    @Override
    public UserType convertToEntityAttribute(String dbData) {
        return UserType.fromDescription(dbData);
    }
}