package com.example.practicetrain.Service;

import com.example.practicetrain.Repository.TrainRepository;
import com.example.practicetrain.Entity.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {
    private  final TrainRepository trainRepository;
    @Autowired
    public TrainService(TrainRepository trainRepository){
        this.trainRepository=trainRepository;}
    public List<Train> getTrains(){
        return trainRepository.findAll();
    }
    }

