package com.example.demo.utils.mapper.imp;

import com.example.demo.dto.CropDTO;
import com.example.demo.model.agriculture.Crop;
import com.example.demo.utils.mapper.CropMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CropMapperImp implements CropMapper {

    @Override
    public CropDTO toDTO(Crop crop) {
        if (crop == null) return null;

        CropDTO cropDTO = new CropDTO();
        cropDTO.setId(crop.getId());
        cropDTO.setName(crop.getName());
        cropDTO.setRootHeight(crop.getRootHeight());
        cropDTO.setWaterRequired(crop.getWaterRequired());

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
    public List<Crop> toEntityList(List<CropDTO> cropRespons) {
        if (cropRespons == null) return null;

        List<Crop> list = new ArrayList<>( cropRespons.size() );
        for (CropDTO cropDTO : cropRespons) {
            list.add( toEntity(cropDTO));
        }
        return list;
    }
}
