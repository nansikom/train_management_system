package com.example.practicetrain.Controller;
import com.example.practicetrain.Entity.Train;
import com.example.practicetrain.Service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path ="/api/v/train")
public class TrainController {
    private final TrainService trainService;
    @Autowired
    public TrainController(TrainService trainService){
        this.trainService = trainService;

    }
    @GetMapping
    public List<Train> getTrains(){
        return trainService.getTrains();
    }
}
