package com.example.practicetrain.Entity;
import java.time.LocalDateTime;
public class Schedule {
    private Long schedule_id;
    private Long train_id;
    private Long station_id;
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

    public Long getTrain_id() {
        return train_id;
    }

    public void setTrain_id(Long train_id) {
        this.train_id = train_id;
    }

    public Long getStation_id() {
        return station_id;
    }

    public void setStation_id(Long station_id) {
        this.station_id = station_id;
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



    public Schedule(Long train_id, Long station_id, LocalDateTime departure_time, LocalDateTime arrival_time) {
        this.train_id = train_id;
        this.station_id = station_id;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
    }

    public Schedule(Long schedule_id, Long train_id, Long station_id, LocalDateTime departure_time, LocalDateTime arrival_time) {
        this.schedule_id = schedule_id;
        this.train_id = train_id;
        this.station_id = station_id;
        this.departure_time = departure_time;
        this.arrival_time = arrival_time;
    }


}
