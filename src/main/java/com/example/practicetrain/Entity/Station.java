package com.example.practicetrain.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Station {
    public Station(Long id, String stationName, Integer platformCount) {
        this.id = id;
        this.stationName = stationName;
        this.platformCount = platformCount;
    }

    public Station() {
    }

    public Station(String stationName, Integer platformCount) {
        this.stationName = stationName;
        this.platformCount = platformCount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Integer getPlatformCount() {
        return platformCount;
    }

    public void setPlatformCount(Integer platformCount) {
        this.platformCount = platformCount;
    }

    private String stationName;
    private Integer platformCount;
}
