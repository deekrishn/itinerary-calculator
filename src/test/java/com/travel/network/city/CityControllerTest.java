package com.travel.network.city;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.network.CityService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private CityService cityService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void addCityTest() throws Exception {
        String jsonRequest = "{\"name\":\"Chennai\",\"country\":\"India\"}";

        mockMvc.perform(post("/cities/")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllCitiesTest() throws Exception {
        mockMvc.perform(get("/cities/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

        City city = new City("Mumbai", "India");
        cityService.addCity(city);

        mockMvc.perform(get("/cities/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"country\": \"India\",\"name\": \"Mumbai\"}]"));
    }

}