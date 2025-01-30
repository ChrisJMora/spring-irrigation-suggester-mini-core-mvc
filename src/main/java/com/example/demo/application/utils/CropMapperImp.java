package com.example.demo.application.utils;

import com.example.demo.application.dto.CropDTO;
import com.example.demo.application.ports.mappers.LocationMapper;
import com.example.demo.domain.models.Crop;
import com.example.demo.application.ports.mappers.CropMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CropMapperImp implements CropMapper {

    private final LocationMapper locationMapper;

    public CropMapperImp(LocationMapper locationMapper) {
        this.locationMapper = locationMapper;
    }

    @Override
    public CropDTO toDTO(Crop crop) {
        if (crop == null) return null;

        CropDTO cropDTO = new CropDTO();
        cropDTO.setId(crop.getId());
        cropDTO.setName(crop.getName());
        cropDTO.setRootHeight(crop.getRootHeight());
        cropDTO.setWaterRequired(crop.getWaterRequired());
        cropDTO.setLocation(locationMapper.toDto(crop.getLocation()));

        return cropDTO;
    }

    @Override
    public Crop toEntity(CropDTO cropDTO) {
        if (cropDTO == null) return null;

        Crop crop = new Crop();
        crop.setId(cropDTO.getId());
        crop.setName(cropDTO.getName());
        crop.setRootHeight(cropDTO.getRootHeight());
        crop.setWaterRequired(cropDTO.getWaterRequired());
        crop.setLocation(locationMapper.toEntity(cropDTO.getLocation()));

        return crop;
    }

    @Override
    public List<CropDTO> toDtoList(List<Crop> crops) {
        if (crops == null) return null;

        List<CropDTO> list = new ArrayList<>( crops.size() );
        for ( Crop crop : crops ) {
            list.add( toDTO(crop) );
        }
        return list;
    }

    @Override
    public List<Crop> toEntityList(List<CropDTO> cropResponse) {
        if (cropResponse == null) return null;

        List<Crop> list = new ArrayList<>( cropResponse.size() );
        for (CropDTO cropDTO : cropResponse) {
            list.add( toEntity(cropDTO));
        }
        return list;
    }
}
