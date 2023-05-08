package tn.esprit.healthcloud.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.healthcloud.entities.Consultation;

import tn.esprit.healthcloud.repositories.AssuranceRepository;
import tn.esprit.healthcloud.repositories.ConsultationRepository;
import tn.esprit.healthcloud.repositories.FactureRepository;

import javax.mail.MessagingException;


import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class ConsultationService implements IConsultationService {

    private final ConsultationRepository consultationRepository;
    private final AssuranceRepository  assuranceRepository;
    private final FactureRepository factureRepository;
    public ConsultationService(ConsultationRepository consultationRepository, AssuranceRepository assuranceRepository, FactureRepository factureRepository) {
        this.consultationRepository = consultationRepository;
        this.assuranceRepository = assuranceRepository;
        this.factureRepository = factureRepository;
    }


    @Override
    public Consultation createConsultation(Consultation consultation) {
        Consultation savedConsultation = consultationRepository.save(consultation);
        return savedConsultation;
    }

    @Override
    public Consultation getConsultationById(Long id) {
        Consultation consultation = consultationRepository.findById(id).get();
        return consultation;
    }

    @Override
    public List<Consultation> getAllConsultations() {
        List<Consultation> consultations = consultationRepository.findAll();
        return consultations;
    }


    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id parameter cannot be null");
        }
        consultationRepository.deleteById(id);
    }

    public Consultation updateConsultation(Long id, Consultation consultation) {
        Optional<Consultation> existingConsultation = consultationRepository.findById(id);

        if (existingConsultation.isPresent()) {
            Consultation updatedConsultation = existingConsultation.get();
            updatedConsultation.setDate(consultation.getDate());
            updatedConsultation.setTime(consultation.getTime());
            updatedConsultation.setStatusRDV(consultation.getStatusRDV());
            updatedConsultation.setSuivi(consultation.getSuivi());

            return consultationRepository.save(updatedConsultation);
        } else {
            throw new NoSuchElementException("Consultation with id " + id + " not found");
        }
    }


public class ReminderService {


    @Autowired
    private Emailreminder emailService;

    @Scheduled(cron = "0 0 9 * * *") // Runs every day at 9am
    public void sendReminders() throws MessagingException {
        List<Consultation> upcomingConsultations = consultationRepository.findUpcomingConsultations(new Date());
        for (Consultation consultation : upcomingConsultations) {
            // Code to send reminders to patients via email or text message
            String email = consultation.getPatient().getEmailP();
            String subject = "Reminder: Your consultation is coming up soon!";
            String body = "Dear " + consultation.getPatient().getNomP() + ",\n\n"
                    + "This is a reminder that your consultation with Dr. " + consultation.getDoctor().getNom()
                    + " is scheduled for " + consultation.getDate() + ".\n\n"
                    + "Please arrive at least 15 minutes prior to your scheduled appointment time.\n\n"
                    + "Thank you for choosing our hospital.\n\n" + "Sincerely,\n\n" + "The Hospital Team";
            emailService.sendEmailreminder(email, subject, body);
        }
    }
}

}







