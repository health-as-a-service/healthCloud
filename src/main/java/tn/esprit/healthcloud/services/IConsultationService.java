package tn.esprit.healthcloud.services;
import tn.esprit.healthcloud.entities.Assurance;
import tn.esprit.healthcloud.entities.Consultation;
import tn.esprit.healthcloud.entities.Facture;

import java.util.Date;
import java.util.List;

public interface IConsultationService {
        Consultation createConsultation(Consultation consultation);
        Consultation getConsultationById(Long id);
        List<Consultation> getAllConsultations();
        void addAntecedentToConsultation(Long consultationId, String antecedent);
        void addFactureToConsultation(Long consultationId, Facture facture);
        void addAssuranceToConsultation(Long consultationId, Assurance assurance);
        void sendReminderEmails();


        List<Consultation> getConsultationsByDateRange(Date startDate, Date endDate);
        void exportConsultationData(String format);
      //  Prescription createPrescription(Prescription prescription);

      //  List<PatientConsultation> getPatientConsultationHistory(Long patientId);
}




