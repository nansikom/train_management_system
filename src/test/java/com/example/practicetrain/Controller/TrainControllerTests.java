package com.example.practicetrain.Controller;

import com.example.practicetrain.Entity.Route;
import com.example.practicetrain.Entity.Train;
import com.example.practicetrain.Service.TrainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TrainController.class)
class TrainControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TrainService trainService;

    @Test
    void getRoutesAcceptsUrlWithLongSuffix() throws Exception {
        when(trainService.getTrainRoutes(1L))
                .thenReturn(List.of(new Route("Okinawa", "Tokyo", 145)));

        mockMvc.perform(get("/1L/routes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].origin_station").value("Okinawa"))
                .andExpect(jsonPath("$[0].destination_station").value("Tokyo"))
                .andExpect(jsonPath("$[0].distance").value(145));
    }

    @Test
    void getStatusUsesStatusPathVariable() throws Exception {
        when(trainService.getstatus("active"))
                .thenReturn(List.of(new Train("PowTrail", 200, "active")));

        mockMvc.perform(get("/v1/train/status/active"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("PowTrail"))
                .andExpect(jsonPath("$[0].capacity").value(200))
                .andExpect(jsonPath("$[0].status").value("active"));
    }

    @Test
    void getCapacityGreaterThanUsesCapacityPathVariable() throws Exception {
        when(trainService.getcapacitygreaterthan(30))
                .thenReturn(List.of(new Train("PowTrail", 200, "active")));

        mockMvc.perform(get("/v1/train/capacity-greater-than/30"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("PowTrail"))
                .andExpect(jsonPath("$[0].capacity").value(200))
                .andExpect(jsonPath("$[0].status").value("active"));
    }
}
