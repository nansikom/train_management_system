package com.example.practicetrain.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Route {
    @Id
    @SequenceGenerator(
            name = "route_sequence",
            sequenceName = "route_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "route_sequence"
    )
    private Long id;
    @Column(name = "origin_station")
    private String originStation;
    @Column(name = "destination_station")
    private String destinationStation;
    private Integer distance;
    //Every route belongs to one train
    // runs a query thru this and returns the result
    @OneToMany(
            mappedBy = "route",
            cascade = CascadeType.ALL
    )
    @JsonIgnore
    // Creating the list of routes
    private List<Schedule> schedules;
    public void setSchedules(List<Schedule> schedules){
        this.schedules = schedules;
    }
    public List<Schedule> getSchedules(){
        return schedules;
    }

    public Route(Long id, String originStation, String destinationStation, Integer distance) {
        this.id = id;
        this.originStation = originStation;
        this.destinationStation = destinationStation;
        this.distance = distance;
    }
    public Route(String originStation, String destinationStation, Integer distance) {
        this.originStation = originStation;
        this.destinationStation = destinationStation;
        this.distance = distance;
    }

    public Route() {

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginStation() {
        return originStation;
    }

    public void setOriginStation(String originStation) {
        this.originStation = originStation;
    }

    public String getDestinationStation() {
        return destinationStation;
    }

    public void setDestinationStation(String destinationStation) {
        this.destinationStation = destinationStation;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}
