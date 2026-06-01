package com.example.practicetrain.Controller;

import com.example.practicetrain.DTO.ScheduleDto;
import com.example.practicetrain.Entity.Route;
import com.example.practicetrain.Entity.Train;
import com.example.practicetrain.Service.TrainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.time.LocalDateTime;

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
    void getTrainsReturnsAllTrains() throws Exception {
        when(trainService.getTrains())
                .thenReturn(List.of(new Train("PowTrail", 200, "active")));

        mockMvc.perform(get("/v1/train"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("PowTrail"))
                .andExpect(jsonPath("$[0].capacity").value(200))
                .andExpect(jsonPath("$[0].status").value("active"));
    }

    @Test
    void getRoutesAcceptsUrlWithLongSuffix() throws Exception {
        when(trainService.getTrainRoutes(1L))
                .thenReturn(List.of(new Route("Okinawa", "Tokyo", 145)));

        mockMvc.perform(get("/1L/routes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].originStation").value("Okinawa"))
                .andExpect(jsonPath("$[0].destinationStation").value("Tokyo"))
                .andExpect(jsonPath("$[0].distance").value(145));
    }

    @Test
    void getStatusUsesStatusPathVariable() throws Exception {
        when(trainService.getStatus("active"))
                .thenReturn(List.of(new Train("PowTrail", 200, "active")));

        mockMvc.perform(get("/v1/train/status/active"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("PowTrail"))
                .andExpect(jsonPath("$[0].capacity").value(200))
                .andExpect(jsonPath("$[0].status").value("active"));
    }

    @Test
    void getCapacityGreaterThanUsesCapacityPathVariable() throws Exception {
        when(trainService.getCapacityGreaterThan(30))
                .thenReturn(List.of(new Train("PowTrail", 200, "active")));

        mockMvc.perform(get("/v1/train/capacity-greater-than/30"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("PowTrail"))
                .andExpect(jsonPath("$[0].capacity").value(200))
                .andExpect(jsonPath("$[0].status").value("active"));
    }

    @Test
    void getSchedulesReturnsDtoPayload() throws Exception {
        when(trainService.getTrainSchedules(1L))
                .thenReturn(List.of(new ScheduleDto(
                        1L,
                        LocalDateTime.of(2026, 1, 1, 8, 0),
                        LocalDateTime.of(2026, 1, 1, 10, 30),
                        1L,
                        "TuckleBuster",
                        19L,
                        "Okinawa",
                        "Tokyo",
                        145
                )));

        mockMvc.perform(get("/showschedules/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].scheduleId").value(1))
                .andExpect(jsonPath("$[0].trainName").value("TuckleBuster"))
                .andExpect(jsonPath("$[0].originStation").value("Okinawa"))
                .andExpect(jsonPath("$[0].destinationStation").value("Tokyo"));
    }
}
