package com.travel.network;

import com.travel.network.city.City;
import com.travel.network.city.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getAllCities() {
        List<City> list = new ArrayList<>();
        cityRepository.findAll().forEach(list::add);
        return list;
    }

    public void addCity(City city) {
       cityRepository.save(city);
    }
}
