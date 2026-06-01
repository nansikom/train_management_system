package com.example.practicetrain.Controller;

import com.example.practicetrain.DTO.ScheduleDto;
import com.example.practicetrain.Service.ScheduleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = {"", "/api"})
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping(path = "/v1/schedule")
    public List<ScheduleDto> getSchedules() {
        return scheduleService.getSchedules();
    }

    @GetMapping(path = "/v1/schedule/train/{id}")
    public List<ScheduleDto> getSchedulesByTrainId(@PathVariable Long id) {
        return scheduleService.getSchedulesByTrainId(id);
    }
}
