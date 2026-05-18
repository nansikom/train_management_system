package com.example.practicetrain.Entity;

import jakarta.persistence.*;

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
    private String origin_station;
    private  String destination_station;
    private Integer  distance;
    //Every route belongs to one train
    // runs a query thru this and returns the result
    @ManyToOne
    @JoinColumn(name="train_id")
    //map object relationship linking train_id as foreign key
    // saves them at the creation in the repo coz route has a field named train and train has id
    private Train train;
    public void setTrain(Train train){
        this.train = train;
    }

    public Route(Long id, String origin_station, String destination_station, Integer distance) {
        this.id = id;
        this.origin_station = origin_station;
        this.destination_station = destination_station;
        this.distance = distance;
    }
    public Route(String origin_station, String destination_station, Integer distance) {
        this.origin_station = origin_station;
        this.destination_station = destination_station;
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

    public String getOrigin_station() {
        return origin_station;
    }

    public void setOrigin_station(String origin_station) {
        this.origin_station = origin_station;
    }

    public String getDestination_station() {
        return destination_station;
    }

    public void setDestination_station(String destination_station) {
        this.destination_station = destination_station;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}
