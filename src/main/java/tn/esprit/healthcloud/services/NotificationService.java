package esprit.etudiant.tn.healthcloud.services;

import esprit.etudiant.tn.healthcloud.entities.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements InotificationService {




    @Override
    public void sendNotification(String message) {
        // Code to send notification
        System.out.println("Notification sent: " + message);
    }


}
