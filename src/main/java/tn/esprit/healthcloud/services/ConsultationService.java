package tn.esprit.healthcloud.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tn.esprit.healthcloud.entities.Assurance;
import tn.esprit.healthcloud.entities.Consultation;
import tn.esprit.healthcloud.entities.Facture;
import tn.esprit.healthcloud.repositories.ConsultationRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ConsultationService implements IConsultationService {

    private final ConsultationRepository consultationRepository;

    public ConsultationService(ConsultationRepository consultationRepository, JavaMailSender javaMailSender) {
        this.consultationRepository = consultationRepository;
        this.javaMailSender = javaMailSender;
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
        return consultations.stream().map(consultation -> consultation).collect(Collectors.toList());
    }

    @Override
    public void addAntecedentToConsultation(Long consultationId, String antecedent) {
        Consultation consultation = consultationRepository.findById(consultationId).get();
        consultation.getAntecedentsMedicaux().add(antecedent);
        consultationRepository.save(consultation);
    }

    @Override
    public void addFactureToConsultation(Long consultationId, Facture facture) {
        Consultation consultation = consultationRepository.findById(consultationId).get();
        consultation.setFacture(facture);
        consultationRepository.save(consultation);
    }

    @Override
    public void addAssuranceToConsultation(Long consultationId, Assurance assurance) {
        Consultation consultation = consultationRepository.findById(consultationId).get();
        consultation.setAssurance(assurance);
        consultationRepository.save(consultation);
    }

    @Override
    public void sendReminderEmails() {
        List<Consultation> consultations = consultationRepository.findAll();
        for (Consultation consultation : consultations) {
            LocalDateTime consultationDateTime = consultation.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().with(consultation.getTime());
            LocalDateTime twoDaysBefore = LocalDateTime.now().plusDays(2);
            if (consultationDateTime.isBefore(twoDaysBefore)) {
                String recipientEmail = "baleki3539@snowlash.com"; /*consultation.getPatient().getEmail();*/
                String subject = "Reminder: Consultation on " + consultation.getDate() + " at " + consultation.getTime();
                String body = "Dear " + "consultation.getPatient().getFirstName()" + ",\n\nThis is a reminder that you have a consultation scheduled with Dr. "
                        + "consultation.getDoctor().getLastName()" + " on " + consultation.getDate() + " at " + consultation.getTime()
                        + ". Please arrive on time and bring any necessary documents.\n\nBest regards,\nMedical Clinic";
                sendEmail(recipientEmail, subject, body);
            }
        }
    }
    private final JavaMailSender javaMailSender;
    private void sendEmail(String recipientEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }


    @Override
    public List<Consultation> getConsultationsByDateRange(Date startDate, Date endDate) {
        List<Consultation> consultations = consultationRepository.findByDateBetween(startDate, endDate);
        return consultations.stream()
                .map(c -> c)
                .collect(Collectors.toList());
    }

    @Override
    public void exportConsultationData(String format) {
        List<Consultation> consultations = consultationRepository.findAll();
        if (format.equalsIgnoreCase("csv")) {
            try {
                FileWriter writer = new FileWriter("consultations.csv");
                writer.append("Doctor, Patient, Date, Time, Comment\n");
                for (Consultation consultation : consultations) {
                    /* writer.append(consultation.getDoctor().getLastName() + ", ");
                    writer.append(consultation.getPatient().getLastName() + ", ");*/
                    writer.append(consultation.getDate().toString() + ", ");
                    writer.append(consultation.getTime().toString() + ", ");
                    writer.append(consultation.getComment() + "\n");
                }
                writer.flush();
                writer.close();
                System.out.println("Consultation data exported as CSV file.");
            } catch (IOException e) {
                System.out.println("An error occurred while exporting the consultation data as a CSV file.");
                e.printStackTrace();
            }
        } else if (format.equalsIgnoreCase("json")) {
            // Export the data as a JSON file
        } else {
            System.out.println("Invalid format specified.");
        }
    }

}


