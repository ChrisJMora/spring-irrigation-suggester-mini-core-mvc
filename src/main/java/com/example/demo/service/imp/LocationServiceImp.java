package com.example.demo.service.imp;

import com.example.demo.dto.LocationDTO;
import com.example.demo.exception.EmptyTableException;
import com.example.demo.model.agriculture.Location;
import com.example.demo.model.httpResponse.WrappedEntity;
import com.example.demo.persistence.LocationRepository;
import com.example.demo.service.LocationService;
import com.example.demo.utils.mapper.LocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class LocationServiceImp implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationMapper locationMapper;

    /**
     * Get all locations from database, if the table is empty then throw an
     * exception.
     * @return List of the locations founded in the database.
     * @exception EmptyTableException When the table have not records.
     */
    @Override
    public List<Location> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        if (locations.isEmpty()) throw new EmptyTableException(Location.class);
        return locations;
    }

    /**
     * Get a random location from the database, if the table is empty then
     * thrown an exception.
     * @return Random location from the database.
     * @exception EmptyTableException When the table have not records.
     */
    @Override
    public Location getRandomLocation() {
        List<Location> locations = getAllLocations();
        Random random = new Random();
        int randomIndex = random.nextInt(locations.size());
        return locations.get(randomIndex);
    }
}
