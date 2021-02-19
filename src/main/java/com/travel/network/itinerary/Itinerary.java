package com.travel.network.itinerary;

import com.travel.network.flight.Flight;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

public class Itinerary {
    @ApiModelProperty(notes = "List of the flights you need to take to have the cheapest fare", name = "Itinerary")
    private List<Flight> itin;

    @ApiModelProperty(notes = "Final cheapest fare when you take this itinerary", name = "Final Fare")
    private float fare;

    public Itinerary(List<Flight> itin, float fare) {
        this.itin = itin;
        this.fare = fare;
    }

    public List<Flight> getItin() {
        return itin;
    }

    public float getFare() {
        return fare;
    }

    public void setFare(float fare) {
        this.fare = fare;
    }

}
