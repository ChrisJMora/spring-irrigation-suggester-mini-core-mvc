package com.example.demo.utils.mapper;

import com.example.demo.dto.SensorRecordDTO;
import com.example.demo.model.agriculture.Sensor;
import com.example.demo.model.agriculture.SensorRecord;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-23T16:23:50-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class SensorRecordMapperImpl implements SensorRecordMapper {

    @Override
    public SensorRecordDTO toDto(SensorRecord sensorRecord) {
        if ( sensorRecord == null ) {
            return null;
        }

        SensorRecordDTO sensorRecordDTO = new SensorRecordDTO();

        return sensorRecordDTO;
    }

    @Override
    public SensorRecord toEntity(SensorRecordDTO sensorRecordDTO) {
        if ( sensorRecordDTO == null ) {
            return null;
        }

        Sensor sensor = null;

        SensorRecord sensorRecord = new SensorRecord( sensor );

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
