package com.example.practicetrain;

import com.example.practicetrain.Entity.Route;
import com.example.practicetrain.Entity.Train;
import com.example.practicetrain.Repository.RouteRepository;
import com.example.practicetrain.Repository.TrainRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;
// set up code when the application starts
@Configuration
public class TrainConfig {
    // @Bean to connect us to the repository class so we are able to inject into the repository class
    @Bean
    CommandLineRunner commandLineRunner(TrainRepository trainrepository, RouteRepository routerepository) {
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

            if (routerepository.countByTrainId(z.getId()) == 0) {
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
                r1.setTrain(t);
                r2.setTrain(t);
                routerepository.saveAll(List.of(r1, r2));

            }
            if (routerepository.countByTrainId(y.getId()) == 0) {
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
                r3.setTrain(y);
                r4.setTrain(y);
                routerepository.saveAll(List.of(r3, r4));

            }
            if (routerepository.countByTrainId(z.getId()) == 0) {
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
                r5.setTrain(z);
                r6.setTrain(z);
                routerepository.saveAll(List.of(r5, r6));

            }
                // takes the java object converts it into a sql and saves it to the dn


                }
                ;
            }
            ;
        };






