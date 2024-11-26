package com.example.demo.service.imp;

import com.example.demo.exception.EmptyTableException;
import com.example.demo.exception.SaveRecordFailException;
import com.example.demo.model.agriculture.Crop;
import com.example.demo.model.agriculture.Forecast;
import com.example.demo.model.httpResponse.WrappedEntity;
import com.example.demo.persistence.CropRepository;
import com.example.demo.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropServiceImp implements CropService {

    @Autowired
    private CropRepository cropRepository;

    /**
     * Get all crops from database, if the table is empty then throw an
     * exception.
     * @return List of the crops founded in the database.
     * @exception EmptyTableException When the table have not records.
     */
    @Override
    public List<Crop> getAllCrops() {
        List<Crop> crops = cropRepository.findAll();
        if (crops.isEmpty()) throw new EmptyTableException(Crop.class);
        return cropRepository.findAll();
    }

    /**
     * Create or update a crop in the database, if the crop is not
     * saved then throw an exception.
     * @param crop The crop that will be saved.
     * @exception SaveRecordFailException When the record couldn't been saved.
     */
    @Override
    public Crop saveCrop(Crop crop) {
        Crop savedCrop = cropRepository.save(crop);
        if (savedCrop.getId() == null) throw new SaveRecordFailException(Crop.class);
        return savedCrop;
    }
}
