package com.travel.network.flight;

import com.travel.network.city.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private CityRepository cityRepository;

    public List<Flight> getAllFlights() {
        List<Flight> list = new ArrayList<>();
        flightRepository.findAll().forEach(list::add);
        return list;
    }

    public Optional<Flight> getFlight(long id) {
        return flightRepository.findById(id);
    }

    public long addFlight(Flight flight) {
        if (!cityRepository.existsById(flight.getSource().getName()) ||
                !cityRepository.existsById(flight.getDestination().getName())) return -1;
        return flightRepository.save(flight).getId();
    }

    public long updateFlight(Flight flight) {
        if (!cityRepository.existsById(flight.getSource().getName()) ||
                !cityRepository.existsById(flight.getDestination().getName())) return -1;
        return flightRepository.save(flight).getId();
    }

    public void deleteFlight(long id) throws Exception {
        if (!flightRepository.existsById(id)) throw new Exception("Flight cannot be deleted as it does not exist");
        flightRepository.deleteById(id);
    }

}
