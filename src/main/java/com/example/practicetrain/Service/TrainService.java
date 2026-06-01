package com.example.practicetrain.Service;

import com.example.practicetrain.DTO.ScheduleDto;
import com.example.practicetrain.Entity.Route;
import com.example.practicetrain.Entity.Schedule;
import com.example.practicetrain.Repository.RouteRepository;
import com.example.practicetrain.Repository.ScheduleRepository;
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
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public TrainService(TrainRepository trainRepository, RouteRepository routeRepository, ScheduleRepository scheduleRepository) {
        this.trainRepository = trainRepository;
        this.routeRepository = routeRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public List<Train> getTrains() {
        return trainRepository.findAll();
    }


    // use the databse id to getthe train routes.
    public List<Route> getTrainRoutes(Long id) {
        // fetch all the routes rows where route.trainid == this train's id
        trainRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Train not found with id: " + id));
        //uses schedule to find the appropriate routes for the train since its the linker
        return scheduleRepository.findByTrain_Id(id)
                .stream()
                .map(Schedule::getRoute)
                .filter(Objects::nonNull)
                .toList();
    }
    public List<ScheduleDto> getTrainSchedules(Long id){
        trainRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Train not found with id: " + id));
        return scheduleRepository.findByTrain_Id(id)
                .stream()
                .map(ScheduleDto::fromEntity)
                .toList();
    }
    public List<Train> getStatus(String status){
        return trainRepository.findByStatus(status);
    }
    public List<Train> getCapacityGreaterThan(Integer capacity){
        return trainRepository.findByCapacityGreaterThan(capacity);
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
    public void updateTrainName(String name, String newName,  String status, Integer capacity){
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
