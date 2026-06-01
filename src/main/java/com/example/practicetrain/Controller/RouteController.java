package com.example.practicetrain.Controller;

import com.example.practicetrain.Entity.Route;
import com.example.practicetrain.Service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//api Layer dp
@RestController
@RequestMapping(path = {"", "/api"})

public class RouteController {
    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping(path = "/v1/route")
    public List<Route> getRoutes() {
        return routeService.getRoutes();
    }

    @GetMapping(path = "/distancegreater/{distance}")
    public List<Route> distanceGreaterThan(@PathVariable Integer distance) {
        return routeService.distanceGreaterThan(distance);
    }

    // since ur adding something for post it is request body
    @PostMapping(path = "/addroute")
    public List<Route> addRoute(@RequestBody Route route) {
        return routeService.addRoute(route);
    }

    @DeleteMapping(path = "/deleteroute/{id}")
    public void deletebyid(@PathVariable Long id) {
        routeService.deletebyid(id);
    }
    @PutMapping (path ="/updateByOriginStation/{id}")
    public void updateoriginstation(@PathVariable("id")Long id, @RequestParam(required = false) String originStation){
        routeService.updateoriginStation(originStation, id);

    }
}


