package com.example.practicetrain.DTO;

import com.example.practicetrain.Entity.Schedule;

import java.time.LocalDateTime;

public record ScheduleDto(
        Long scheduleId,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        Long trainId,
        String trainName,
        Long routeId,
        String originStation,
        String destinationStation,
        Integer distance
) {
    public static ScheduleDto fromEntity(Schedule schedule) {
        return new ScheduleDto(
                schedule.getSchedule_id(),
                schedule.getDeparture_time(),
                schedule.getArrival_time(),
                schedule.getTrain() == null ? null : schedule.getTrain().getId(),
                schedule.getTrain() == null ? null : schedule.getTrain().getName(),
                schedule.getRoute() == null ? null : schedule.getRoute().getId(),
                schedule.getRoute() == null ? null : schedule.getRoute().getOriginStation(),
                schedule.getRoute() == null ? null : schedule.getRoute().getDestinationStation(),
                schedule.getRoute() == null ? null : schedule.getRoute().getDistance()
        );
    }
}
