package com.example.practicetrain.Controller;

import com.example.practicetrain.Entity.Route;
import com.example.practicetrain.Service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//api Layer dp
@RestController
@RequestMapping(path="api/v1/route")
public class RouteController {
    private final RouteService routeService;
    @Autowired
    public RouteController(RouteService routeService){
        this.routeService = routeService;
    }
    @GetMapping
    public List<Route> getRoutes(){
        return routeService.getRoutes();
    }
}
