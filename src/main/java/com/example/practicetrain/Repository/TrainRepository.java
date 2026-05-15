package com.example.practicetrain.Repository;

import com.example.practicetrain.Entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository  extends JpaRepository<Train, Long > {
}
