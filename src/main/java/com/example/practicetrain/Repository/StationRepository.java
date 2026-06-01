package com.example.practicetrain.Repository;

import com.example.practicetrain.Entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {
}
