package com.example.practicetrain.Controller;
import com.example.practicetrain.Entity.Route;
import com.example.practicetrain.Entity.Train;
import com.example.practicetrain.Service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = {"", "/api"})
public class TrainController {
    private final TrainService trainService;
    @Autowired
    public TrainController(TrainService trainService){
        this.trainService = trainService;
    }
    @GetMapping(path="/v1/train")
    public List<Train> getTrains(){
        return trainService.getTrains();
    }
    //was missing a path variable especially if you are getting it from the url
    @GetMapping(path="/v1/train/status/{status}")
    public List<Train> getstatus(@PathVariable String status){
        return trainService.getstatus(status);
    }
    @GetMapping(path="/v1/train/capacity-greater-than/{capacity}")
    public List<Train> getcapacitygreaterthan(@PathVariable Integer capacity){
        return trainService.getcapacitygreaterthan(capacity);
    }
    // made to accept multiple behaviour
    @GetMapping(path={"/{id}/routes", "/{id}L/routes"})
    public List<Route> getRoutes(
            @PathVariable Long id){
        return trainService.getTrainRoutes(id);
    }
    @GetMapping(path={"/{name}", "/name/{name}"})
    public List<String> get_locations(
            @PathVariable String name ){
        return trainService.get_origin_and_destination(name);
    }
    // Post Mapping route
    @PostMapping(path="/add")
    public void registernewTrain(@RequestBody Train train){
          trainService.addTrain(train);
    }
    @DeleteMapping(path ="/delete/{id}")
    public void deletetrain(@PathVariable Long id){
        trainService.deleteTrain(id);
    }
    @PutMapping(path="/updatebyname/{name}")
    public void updatetrainname(@PathVariable("name") String name,
                                @RequestParam(required = false) String newName,
                                @RequestParam(required = false) String status,
                                @RequestParam(required = false)Integer capacity
                                ){
        trainService.updatetrainname(name, newName, status, capacity);
    }
}


