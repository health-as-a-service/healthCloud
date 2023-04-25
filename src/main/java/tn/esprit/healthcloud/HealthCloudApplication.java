package tn.esprit.healthcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HealthCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthCloudApplication.class, args);
    }

}
