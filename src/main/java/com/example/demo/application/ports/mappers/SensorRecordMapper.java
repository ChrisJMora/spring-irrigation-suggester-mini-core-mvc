package com.example.demo.application.ports.mappers;

import com.example.demo.application.dto.SensorRecordDTO;
import com.example.demo.domain.models.SensorRecord;

import java.util.List;

public interface SensorRecordMapper {
    SensorRecordDTO toDto(SensorRecord sensorRecorde);
    SensorRecord toEntity(SensorRecordDTO sensorRecordDTO);
    List<SensorRecordDTO> toDtoList(List<SensorRecord> sensorRecords);
    List<SensorRecord> toEntityList(List<SensorRecordDTO> sensorRecordDTOs);
}
