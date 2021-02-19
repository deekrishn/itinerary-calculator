package com.travel.network.flight;

import com.travel.network.city.City;
import com.travel.network.city.ErrorResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find all the flights",
            notes = "Gets you the list of all flights we have in the database",
            response = Flight.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = City.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = ErrorResponse.class)
    })
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping(value ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find a specific flight",
            notes = "Gets you the flight corresponding to flight number (id)",
            response = Flight.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = Flight.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = ErrorResponse.class)
    })
    public Optional<Flight> getFlight(@PathVariable long id) {
        return flightService.getFlight(id);
    }

    @PostMapping("/")
    @ApiOperation(value = "Add a new flight",
            notes = "This method adds a new flight")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = Long.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = ErrorResponse.class)
    })
    public long addFlight(@ApiParam("Flight information for new flight to be created") @RequestBody Flight flight) {
        return flightService.addFlight(flight);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an existing flight",
            notes = "This method updates an existing flight")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = Long.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = ErrorResponse.class)
    })
    public long updateFlight(@RequestBody Flight flight) {
        return flightService.updateFlight(flight);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an existing flight",
            notes = "This method deletes an existing flight")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 404, message = "NOT FOUND", response = ErrorResponse.class)
    })
    public void deleteFlight(@PathVariable long id) throws Exception {
        flightService.deleteFlight(id);
    }
}
