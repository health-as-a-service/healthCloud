package esprit.etudiant.tn.healthcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@SpringBootApplication
public class HealthCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthCloudApplication.class, args);
    }



}
