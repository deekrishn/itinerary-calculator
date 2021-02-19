package com.travel.network.city;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.network.CityService;
import com.travel.network.flight.Flight;
import com.travel.network.flight.FlightController;
import com.travel.network.flight.FlightService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightControllerTest {
    private MockMvc mockMvc;
    //@Autowired
    //private WebApplicationContext context;
    //@Autowired
    @Mock
    private FlightService flightService;

    @InjectMocks
    private FlightController flightController;

    //ObjectMapper om = new ObjectMapper();

    @Before
    public void setUp() {
        //mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        mockMvc = MockMvcBuilders.standaloneSetup(flightController).build();
    }

    @Test
    public void addFlightTest() throws Exception {
        String jsonRequest = "{\"destination\": { \"name\":\"Chennai\",\"country\" :\"India\"}," +
                "\"fare\": 100, \"source\": { \"name\":\"Mumbai\",\"country\" :\"India\"}}";

        mockMvc.perform(post("/flights/")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllFlightsTest() throws Exception {
        mockMvc.perform(get("/flights/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }
}
