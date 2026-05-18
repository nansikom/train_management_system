package com.example.practicetrain.Repository;

import com.example.practicetrain.Entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TrainRepository  extends JpaRepository<Train, Long > {
    // saves the trains based on their train name
    Optional<Train> findByName(String name);
    @Query("SELECT s FROM Train s WHERE s.status = ?1")
    List<Train> findByStatus(String status);
    @Query("SELECT s FROM Train s WHERE s.capacity > ?1")
    List<Train>findcapacitygreaterthan(Integer capacity);


}
