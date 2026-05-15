package com.example.practicetrain;

import com.example.practicetrain.Entity.Train;
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
    CommandLineRunner commandLineRunner (TrainRepository repository){
        return args -> {
            if (repository.count() == 0) {
                Train t = new Train(
                        "TuckleBuster",
                        40,
                        "late"
                );
                Train a = new Train(
                        "PowTrail",
                        57,
                        "ontime"
                );
                // takes the java object converts it into a sql and saves it to the dn
                repository.saveAll(List.of(t, a));
        }
        };
    }

}
