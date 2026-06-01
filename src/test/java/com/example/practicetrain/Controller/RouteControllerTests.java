package com.example.practicetrain.Controller;

import com.example.practicetrain.Entity.Route;
import com.example.practicetrain.Service.RouteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RouteController.class)
class RouteControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RouteService routeService;

    @Test
    void getRoutesReturnsAllRoutes() throws Exception {
        when(routeService.getRoutes())
                .thenReturn(List.of(new Route("Okinawa", "Tokyo", 145)));

        mockMvc.perform(get("/v1/route"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].originStation").value("Okinawa"))
                .andExpect(jsonPath("$[0].destinationStation").value("Tokyo"))
                .andExpect(jsonPath("$[0].distance").value(145));
    }

    @Test
    void distanceGreaterThanUsesDistancePathVariable() throws Exception {
        when(routeService.distanceGreaterThan(200))
                .thenReturn(List.of(new Route("Salem", "Austin", 240)));

        mockMvc.perform(get("/distancegreater/200"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].originStation").value("Salem"))
                .andExpect(jsonPath("$[0].destinationStation").value("Austin"))
                .andExpect(jsonPath("$[0].distance").value(240));
    }

    @Test
    void addRouteAcceptsJsonBody() throws Exception {
        when(routeService.addRoute(any(Route.class)))
                .thenReturn(List.of(new Route("Corvallis", "Portland", 234)));

        mockMvc.perform(post("/addroute")
                        .contentType("application/json")
                        .content("""
                                {"originStation":"Corvallis","destinationStation":"Portland","distance":234}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].originStation").value("Corvallis"))
                .andExpect(jsonPath("$[0].destinationStation").value("Portland"))
                .andExpect(jsonPath("$[0].distance").value(234));
    }

    @Test
    void deleteRouteUsesPathVariable() throws Exception {
        doNothing().when(routeService).deletebyid(1L);

        mockMvc.perform(delete("/deleteroute/1"))
                .andExpect(status().isOk());
    }

    @Test
    void updateOriginStationUsesRequestParam() throws Exception {
        doNothing().when(routeService).updateoriginStation("Portland", 1L);

        mockMvc.perform(put("/updateByOriginStation/1")
                        .param("originStation", "Portland"))
                .andExpect(status().isOk());
    }
}
