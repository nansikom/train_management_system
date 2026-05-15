package com.example.practicetrain.Entity;

public class Route {
    private Long id;
    private String origin_station;
    private  String destination_station;
    private Integer  distance;
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
