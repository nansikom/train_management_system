package com.example.practicetrain.Repository;

import com.example.practicetrain.Entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


//DB Layer
public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findByDistanceGreaterThan(Integer distance);
    boolean existsByOriginStationAndDestinationStation(String originStation, String destinationStation);
}
