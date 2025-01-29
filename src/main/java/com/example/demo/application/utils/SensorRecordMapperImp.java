package com.example.demo.application.utils;

import com.example.demo.application.dto.SensorRecordDTO;
import com.example.demo.domain.models.SensorRecord;
import com.example.demo.application.ports.mappers.SensorRecordMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SensorRecordMapperImp implements SensorRecordMapper {
    @Override
    public SensorRecordDTO toDto(SensorRecord sensorRecorde) {
        if ( sensorRecorde == null ) {
            return null;
        }

        SensorRecordDTO sensorRecordDTO = new SensorRecordDTO();

        sensorRecordDTO.setHumidity( sensorRecorde.getHumidity() );
        sensorRecordDTO.setDate( sensorRecorde.getDate() );
        sensorRecordDTO.setTime( sensorRecorde.getTime() );

        return sensorRecordDTO;
    }

    @Override
    public SensorRecord toEntity(SensorRecordDTO sensorRecordDTO) {
        if ( sensorRecordDTO == null ) {
            return null;
        }

        SensorRecord sensorRecord = new SensorRecord();

        sensorRecord.setHumidity( sensorRecordDTO.getHumidity() );
        sensorRecord.setDate( sensorRecordDTO.getDate() );
        sensorRecord.setTime( sensorRecordDTO.getTime() );

        return sensorRecord;
    }

    @Override
    public List<SensorRecordDTO> toDtoList(List<SensorRecord> sensorRecords) {
        if ( sensorRecords == null ) {
            return null;
        }

        List<SensorRecordDTO> list = new ArrayList<SensorRecordDTO>( sensorRecords.size() );
        for ( SensorRecord sensorRecord : sensorRecords ) {
            list.add( toDto( sensorRecord ) );
        }

        return list;
    }

    @Override
    public List<SensorRecord> toEntityList(List<SensorRecordDTO> sensorRecordDTOs) {
        if ( sensorRecordDTOs == null ) {
            return null;
        }

        List<SensorRecord> list = new ArrayList<SensorRecord>( sensorRecordDTOs.size() );
        for ( SensorRecordDTO sensorRecordDTO : sensorRecordDTOs ) {
            list.add( toEntity( sensorRecordDTO ) );
        }

        return list;
    }
}
