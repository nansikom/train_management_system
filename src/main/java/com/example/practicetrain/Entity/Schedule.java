package com.example.practicetrain.Entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity

public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schedule_id;
    @ManyToOne
    private Train train;
    @ManyToOne
    private Route route;

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    private LocalDateTime departure_time;

    public Schedule() {
    }

    private LocalDateTime arrival_time;
    public Long getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(Long schedule_id) {
        this.schedule_id = schedule_id;
    }
    public LocalDateTime getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(LocalDateTime departure_time) {
        this.departure_time = departure_time;
    }

    public LocalDateTime getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(LocalDateTime arrival_time) {
        this.arrival_time = arrival_time;
    }



    public Schedule( LocalDateTime departure_time, LocalDateTime arrival_time) {
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
    }

    public Schedule(Long schedule_id, LocalDateTime departure_time, LocalDateTime arrival_time) {
        this.schedule_id = schedule_id;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
    }
}
