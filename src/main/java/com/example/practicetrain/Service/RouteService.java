package com.example.practicetrain.Service;

import com.example.practicetrain.Entity.Route;
import com.example.practicetrain.Repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
//Business Logic Layer
public class RouteService {
    private final RouteRepository routeRepository;
    @Autowired
    public RouteService(RouteRepository routeRepository){
        this.routeRepository = routeRepository;
    }
    public List<Route> getRoutes(){
        return routeRepository.findAll();
    }

}
