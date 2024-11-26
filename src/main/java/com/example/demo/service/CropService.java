package com.example.demo.service;

import com.example.demo.exception.EmptyTableException;
import com.example.demo.exception.SaveRecordFailException;
import com.example.demo.model.agriculture.Crop;
import com.example.demo.model.httpResponse.WrappedEntity;

import java.util.List;

public interface CropService {
    /**
     * Get all crops from database, if the table is empty then throw an
     * exception.
     * @return List of the crops founded in the database.
     * @exception EmptyTableException When the table have not records.
     */
    List<Crop> getAllCrops();

    /**
     * Create or update a crop in the database, if the crop is not
     * saved then throw an exception.
     * @param crop The crop that will be saved.
     * @exception SaveRecordFailException When the record couldn't been saved.
     */
    public Crop saveCrop(Crop crop);
}
