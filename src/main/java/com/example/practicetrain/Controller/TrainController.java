package com.example.practicetrain.Controller;
import com.example.practicetrain.DTO.ScheduleDto;
import com.example.practicetrain.Entity.Route;
import com.example.practicetrain.Entity.Train;
import com.example.practicetrain.Service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(" ")
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
    public List<Train> getStatus(@PathVariable String status){
        return trainService.getStatus(status);
    }
    @GetMapping(path="/v1/train/capacity-greater-than/{capacity}")
    public List<Train> getCapacityGreaterThan(@PathVariable Integer capacity){
        return trainService.getCapacityGreaterThan(capacity);
    }
    // made to accept multiple behaviour
    @GetMapping(path={"/{id}/routes", "/{id}L/routes"})
    public List<Route> getRoutes(
            @PathVariable Long id){
        return trainService.getTrainRoutes(id);
    }
    // get mapping route

    // Post Mapping route
    @PostMapping(path="/add")
    public void registerNewTrain(@RequestBody Train train){
          trainService.addTrain(train);
    }
    @DeleteMapping(path ="/delete/{id}")
    public void deleteTrain(@PathVariable Long id){
        trainService.deleteTrain(id);
    }
    // put rest method
    @PutMapping(path="/updatebyname/{name}")
    public void updateTrainName(@PathVariable("name") String name,
                                @RequestParam(required = false) String newName,
                                @RequestParam(required = false) String status,
                                @RequestParam(required = false)Integer capacity
                                ){
        trainService.updateTrainName(name, newName, status, capacity);
    }
    @GetMapping(path="/showschedules/{id}")
    public List<ScheduleDto> getSchedule(
            @PathVariable Long id){
        return trainService.getTrainSchedules(id);
    }

}

