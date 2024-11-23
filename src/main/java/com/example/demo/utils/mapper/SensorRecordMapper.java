package com.example.demo.utils.mapper;

import com.example.demo.dto.SensorRecordDTO;
import com.example.demo.model.agriculture.SensorRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SensorRecordMapper {
    SensorRecordMapper INSTANCE = Mappers.getMapper(SensorRecordMapper.class);

    SensorRecordDTO toDto(SensorRecord sensorRecord);
    @Mapping(target = "sensor", ignore = true)
    SensorRecord toEntity(SensorRecordDTO sensorRecordDTO);
    List<SensorRecordDTO> toDtoList(List<SensorRecord> sensorRecords);
    List<SensorRecord> toEntityList(List<SensorRecordDTO> sensorRecordDTOs);
}
