package com.travel.network.city;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@ApiModel(description = "City Model")
public class City {
    @Id
    @ApiModelProperty(notes = "Name of the city", name = "name", required = true, value = "Mumbai", example = "Mumbai")
    private String name;

    @ApiModelProperty(notes = "Name of the country", name = "country", value = "India", example = "India")
    private String country;

    public City(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public City() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
