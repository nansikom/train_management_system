package com.example.practicetrain.Service;

import com.example.practicetrain.Entity.Route;
import com.example.practicetrain.Repository.RouteRepository;
import com.example.practicetrain.Repository.TrainRepository;
import com.example.practicetrain.Entity.Train;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Objects;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class TrainService {
    private final TrainRepository trainRepository;
    private final RouteRepository routeRepository;

    @Autowired
    public TrainService(TrainRepository trainRepository, RouteRepository routeRepository) {
        this.trainRepository = trainRepository;
        this.routeRepository = routeRepository;
    }

    public List<Train> getTrains() {
        return trainRepository.findAll();
    }
    public List<String> get_origin_and_destination(String name){
        Train train = trainRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Train not found: " + name));
        return train.getRoutes()
                .stream()
                .map(route ->
                        route.getOrigin_station()
                       + "->"
                       + route.getDestination_station())
                                .toList();

    }

    // use the databse id to getthe train routes.
    public List<Route> getTrainRoutes(Long id) {
        // fetch all the routes rows where route.trainid == this train's id
        trainRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Train not found with id: " + id));
        return routeRepository.findByTrainId(id);
    }
    public List<Train> getstatus(String status){
        return trainRepository.findByStatus(status);
    }
    public List<Train> getcapacitygreaterthan(Integer capacity){
        return trainRepository.findcapacitygreaterthan(capacity);
    }
    public void addTrain(Train train){
        trainRepository.findByName(train.getName())
                .ifPresent(existingTrain -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Train already exists: " + train.getName());
                });

        trainRepository.save(train);
    }
    public void deleteTrain(Long id){
        trainRepository.deleteById(id);
    }
    @Transactional
    public void updatetrainname(String name, String newName,  String status, Integer capacity){
        Train train = trainRepository.findByName(name).orElseThrow(()-> new IllegalArgumentException(
            "train with name " + name + "does not exist"
        ));
       if (newName != null && !newName.isEmpty() && !Objects.equals(train.getName(), newName)){
           train.setName(newName);
       }
       if (status != null && !status.isEmpty()){
           train.setStatus(status);
       }
       if (capacity != null){
           train.setCapacity(capacity);
       }


    }

}
