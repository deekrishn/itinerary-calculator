package com.travel.network.itinerary;

import com.travel.network.city.City;
import com.travel.network.CityService;
import com.travel.network.city.ErrorResponse;
import com.travel.network.flight.Flight;
import com.travel.network.flight.FlightService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.testng.annotations.Test;

import java.util.*;

@RestController
public class ItineraryController {
    @Autowired
    private FlightService flightService;
    @Autowired
    private CityService cityService;

    @GetMapping("/itinerary")
    @ApiOperation(value = "Build itinerary",
            notes = "Finds you the cheapest itinerary having N number of stops",
            response = Itinerary.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = Itinerary.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = ErrorResponse.class)
    })
    public Itinerary getItinerary(String src, String dest, int numberOfStops) {
        City srcCity = null;
        City destCity = null;
        List<City> cities = cityService.getAllCities();
        for (City c : cities) {
            if (srcCity != null && destCity != null) break;
            if (c.getName().equals(src)) {
                srcCity = c;
            }
            if (c.getName().equals(dest)) {
                destCity = c;
            }
        }

        // Source or destination city entered does not exist
        if (srcCity == null || destCity == null) return null;

        return calculateCheapestFlight(srcCity, destCity, numberOfStops);
    }

    // Slight variation of Dijkstra's algorithm keeping in mind the criteria of numberOfStops
    public Itinerary calculateCheapestFlight(City srcCity, City destCity, int numberOfStops) {
        // HashMap mapping each city with the list of flights available from it
        // to another hashmap where the key is the destination city and value is the price of the flight.
        Map<City, Map<City, Flight>> prices = new HashMap<>();
        List<Flight> allFlights = flightService.getAllFlights();

        for(Flight f : allFlights) {
            if(!prices.containsKey(f.getSource()))
                prices.put(f.getSource(), new HashMap<>());
            prices.get(f.getSource()).put(f.getDestination(), f);
        }

        // Priority Queue sorted by minFare so far
        Queue<PQClass> pq = new PriorityQueue<>((o1, o2) -> Float.compare(o1.fare, o2.fare));

        // Fare from srcCity to srcCity is 0. And add that as the first element of PQ.
        pq.add(new PQClass(0.0F, srcCity, numberOfStops + 1, new ArrayList<>()));

        while(!pq.isEmpty()) {
            PQClass c = pq.remove();

            if(c.city.equals(destCity)) return new Itinerary(c.flights, c.fare);;

            // Once number of stops is less than or equal to zero, we cannot proceed further
            // as there is no route
            if(c.numberOfStops > 0) {
                if (prices.containsKey(c.city)) {
                    Map<City, Flight> adj = prices.getOrDefault(c.city, new HashMap<>());
                    for(City adjCity : adj.keySet()) {
                        Flight nextFlight = adj.get(adjCity);
                        List<Flight> currListOfFlights = new ArrayList(Arrays.asList(c.flights));
                        currListOfFlights.add(nextFlight);
                        Float currPrice = nextFlight.getFare();
                        pq.add(new PQClass(c.fare + currPrice, adjCity, c.numberOfStops - 1 ,
                                currListOfFlights));
                    }
                }
            }
        }

        // No route found with that criteria
        return null;
    }

    class PQClass {
        Float fare;
        City city;
        int numberOfStops;
        List<Flight> flights;

        public PQClass(Float fare, City city, int numberOfStops, List<Flight> flights) {
            this.fare = fare;
            this.city = city;
            this.numberOfStops = numberOfStops;
            this.flights = flights;
        }
    }
}
