package com.example.practicetrain.Service;

import com.example.practicetrain.DTO.ScheduleDto;
import com.example.practicetrain.Entity.Schedule;
import com.example.practicetrain.Repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<ScheduleDto> getSchedules() {
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ScheduleDto> getSchedulesByTrainId(Long trainId) {
        return scheduleRepository.findByTrain_Id(trainId)
                .stream()
                .map(ScheduleDto::fromEntity)
                .collect(Collectors.toList());
    }
}
