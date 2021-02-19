package com.travel.network.flight;

import com.travel.network.city.City;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
public class Flight {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @ManyToOne
    @ApiModelProperty(notes = "Name of the source city", name = "From",
            required = true, value = "Mumbai", example = "Mumbai")
    private City source;

    @ManyToOne
    @ApiModelProperty(notes = "Name of the destination city", name = "To",
            required = true, value = "Chennai", example = "Chennai")
    private City destination;

    public Flight(City source, City destination, float fare) {
        this.source = source;
        this.destination = destination;
        this.fare = fare;
    }

    @ApiModelProperty(notes = "Cost of the flight", name = "Fare",
            required = true, value = "100.0", example = "100.0")
    private float fare;

    public Flight() {

    }

    public long getId() {
        return id;
    }

    public City getSource() {
        return source;
    }

    public void setSource(City source) {
        this.source = source;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }

    public float getFare() {
        return fare;
    }

    public void setFare(float fare) {
        this.fare = fare;
    }
}
