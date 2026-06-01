package com.example.practicetrain.Controller;

import com.example.practicetrain.DTO.ScheduleDto;
import com.example.practicetrain.Service.ScheduleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ScheduleController.class)
class ScheduleControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ScheduleService scheduleService;

    @Test
    void getSchedulesReturnsAllSchedules() throws Exception {
        ScheduleDto schedule = new ScheduleDto(
                1L,
                LocalDateTime.of(2026, 1, 1, 8, 0),
                LocalDateTime.of(2026, 1, 1, 10, 30),
                10L,
                "PowTrail",
                19L,
                "Okinawa",
                "Tokyo",
                145
        );

        when(scheduleService.getSchedules()).thenReturn(List.of(schedule));

        mockMvc.perform(get("/v1/schedule"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].scheduleId").value(1))
                .andExpect(jsonPath("$[0].trainName").value("PowTrail"))
                .andExpect(jsonPath("$[0].departureTime").value("2026-01-01T08:00:00"))
                .andExpect(jsonPath("$[0].arrivalTime").value("2026-01-01T10:30:00"))
                .andExpect(jsonPath("$[0].originStation").value("Okinawa"))
                .andExpect(jsonPath("$[0].destinationStation").value("Tokyo"));
    }

    @Test
    void getSchedulesByTrainIdReturnsTrainSchedules() throws Exception {
        ScheduleDto schedule = new ScheduleDto(
                2L,
                LocalDateTime.of(2026, 2, 1, 8, 0),
                LocalDateTime.of(2026, 2, 1, 10, 30),
                1L,
                "TuckleBuster",
                21L,
                "Salem",
                "Austin",
                200
        );

        when(scheduleService.getSchedulesByTrainId(1L)).thenReturn(List.of(schedule));

        mockMvc.perform(get("/v1/schedule/train/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].scheduleId").value(2))
                .andExpect(jsonPath("$[0].trainName").value("TuckleBuster"))
                .andExpect(jsonPath("$[0].originStation").value("Salem"))
                .andExpect(jsonPath("$[0].destinationStation").value("Austin"));
    }
}
