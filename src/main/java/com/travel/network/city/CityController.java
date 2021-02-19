package com.travel.network.city;

import com.travel.network.CityService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find all cities",
            notes = "Gets you the list of all cities we have in the database",
            response = City.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = City.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = ErrorResponse.class)
    })
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @PostMapping(value = "/")
    @ApiOperation(value = "Add a new city",
            notes = "This method adds a new city")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 404, message = "NOT FOUND", response = ErrorResponse.class)
    })
    public void addCity(@ApiParam("City information for new city to be created") @RequestBody City city) {
        cityService.addCity(city);

    }
}
