package com.example.practicetrain;

import com.example.practicetrain.Entity.Route;
import com.example.practicetrain.Entity.Schedule;
import com.example.practicetrain.Entity.Train;
import com.example.practicetrain.Repository.RouteRepository;
import com.example.practicetrain.Repository.ScheduleRepository;
import com.example.practicetrain.Repository.StationRepository;
import com.example.practicetrain.Repository.TrainRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;
// set up code when the application starts
@Configuration
public class TrainConfig {
    // @Bean to connect us to the repository class so we are able to inject into the repository class
    @Bean
    CommandLineRunner commandLineRunner(TrainRepository trainrepository, ScheduleRepository scheduleRepository, RouteRepository routerepository, StationRepository stationRepository) {
        return args -> {
            Train t = trainrepository.findByName("TuckleBuster")
                    .orElseGet(() -> trainrepository.save(new Train(
                            "TuckleBuster",
                            40,
                            "late"
                    )));
            Train y = trainrepository.findByName("SpongeBob")
                    .orElseGet(() -> trainrepository.save(new Train(
                            "SpongeBob",
                            1000,
                            "ontime"
                    )));
            Train z = trainrepository.findByName("PowTrail")
                    .orElseGet(() -> trainrepository.save(new Train(
                            "PowTrail",
                            50,
                            "ontime"
                    )));
            Schedule s1 = new Schedule();
            if (scheduleRepository.countByTrain_Id(t.getId()) == 0) {
                Route r1 = new Route(
                        "Okinawa",
                        "Tokyo",
                        145
                );

                Route r2 = new Route(
                        "Corvallis",
                        "Portland",
                        234
                );
                routerepository.saveAll(List.of(r1, r2));
                s1.setTrain(t);
                s1.setRoute(r1);
                s1.setDeparture_time(LocalDateTime.of(2026,1,1,8,0));
                s1.setArrival_time(LocalDateTime.of(2026,1,1,10,30));
                scheduleRepository.save(s1);

            }
            Schedule s2 = new Schedule();
            if (scheduleRepository.countByTrain_Id(y.getId()) == 0) {
                Route r3 = new Route(
                        "Salem",
                        "Austin",
                        200
                );
                Route r4 = new Route(
                        "Houston",
                        "Dallas",
                        240
                );
                routerepository.saveAll(List.of(r3, r4));
                s2.setTrain(y);
                s2.setRoute(r3);
                s2.setDeparture_time(LocalDateTime.of(2026,02,16,3,4,0));
                s2.setArrival_time(LocalDateTime.of(2026,03,23,3,6));
                scheduleRepository.save(s2);


            }
            Schedule s3 = new Schedule();
            if (scheduleRepository.countByTrain_Id(z.getId()) == 0) {
                Route r5 = new Route(
                        "WestLinn",
                        "LincolinCity",
                        350
                );
                Route r6 = new Route(
                        "Atlanta",
                        "DC",
                        740
                );

                routerepository.saveAll(List.of(r5, r6));
                s3.setTrain(z);
                s3.setRoute(r5);
                s3.setDeparture_time(LocalDateTime.of(2026, 4, 1, 9, 0));
                s3.setArrival_time(LocalDateTime.of(2026, 4, 1, 12, 0));
                scheduleRepository.save(s3);
            }


            //takes the java object converts it into a   and saves it to the dn
                };
            }
            ;
        };




