package com.example.practicetrain.Service;

import com.example.practicetrain.Entity.Route;
import com.example.practicetrain.Repository.RouteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

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
    public List<Route> distanceGreaterThan(Integer distance){
        return routeRepository.findByDistanceGreaterThan(distance);
    }
    public List<Route> addRoute(Route route) {
        if (routeRepository.existsByOriginStationAndDestinationStation(
                route.getOriginStation(),
                route.getDestinationStation()
        )) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Route already exists");
        }
        routeRepository.save(route);
        return routeRepository.findAll();
    }
    public void deletebyid(Long id){
        routeRepository.deleteById(id);


    }
    // ensures that we are able to see changes
    @Transactional
    public void updateoriginStation(String originStation,Long id){
        Route route = routeRepository.findById(id).orElseThrow(() -> new IllegalStateException("" +
                "route id with " + id +"does not exist"));
        //dnt update if the objects are equal
        if (originStation != null && !originStation.isEmpty() && !Objects.equals(route.getOriginStation(), originStation))
            route.setOriginStation(originStation);
    }
}
