package com.example.practicetrain.Repository;

import com.example.practicetrain.Entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//DB Layer
public interface RouteRepository extends JpaRepository<Route, Long> {
    long countByTrainId(Long trainId);
    // reads find by train id and then it goes to go on and look
    List<Route> findByTrainId(Long trainId);
}
