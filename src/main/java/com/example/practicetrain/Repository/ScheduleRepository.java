package com.example.practicetrain.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.practicetrain.Entity.Route;
import com.example.practicetrain.Entity.Schedule;

import java.util.List;
@Repository


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByTrain_Id(Long trainId);
    long countByTrain_Id(Long trainId);

}
