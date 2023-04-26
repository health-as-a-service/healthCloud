package tn.esprit.healthcloud.controllers;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.healthcloud.entities.Assurance;
import tn.esprit.healthcloud.entities.Consultation;
import tn.esprit.healthcloud.entities.Facture;
import tn.esprit.healthcloud.services.ConsultationService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/consultations")
@AllArgsConstructor
public class ConsultationController {


    private ConsultationService consultationService;

    @PostMapping
    @CrossOrigin

    public ResponseEntity<Consultation> createConsultation(@RequestBody Consultation consultation) {
        Consultation createdConsultation = consultationService.createConsultation(consultation);
        return new ResponseEntity<>(createdConsultation, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @CrossOrigin

    public ResponseEntity<Consultation> getConsultationById(@PathVariable Long id) {
        Consultation consultation = consultationService.getConsultationById(id);
        return ResponseEntity.ok(consultation);
    }

    @GetMapping
    public ResponseEntity<List<Consultation>> getAllConsultations() {
        List<Consultation> consultationDTOs = consultationService.getAllConsultations();
        return ResponseEntity.ok(consultationDTOs);
    }

    @PostMapping("/{consultationId}/antecedents")
    @CrossOrigin

    public ResponseEntity<Void> addAntecedentToConsultation(@PathVariable Long consultationId, @RequestBody String antecedent) {
        consultationService.addAntecedentToConsultation(consultationId, antecedent);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{consultationId}/facture")
    @CrossOrigin

    public ResponseEntity<Void> addFactureToConsultation(@PathVariable Long consultationId, @RequestBody Facture facture) {
        consultationService.addFactureToConsultation(consultationId, facture);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{consultationId}/assurance")
    @CrossOrigin

    public ResponseEntity<Void> addAssuranceToConsultation(@PathVariable Long consultationId, @RequestBody Assurance assurance) {
        consultationService.addAssuranceToConsultation(consultationId, assurance);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/date-range")
    @CrossOrigin

    public ResponseEntity<List<Consultation>> getConsultationsByDateRange(@RequestParam("start-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                                             @RequestParam("end-date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        List<Consultation> consultationDTOs = consultationService.getConsultationsByDateRange(startDate, endDate);
        return ResponseEntity.ok(consultationDTOs);
    }

    @GetMapping("/export")
    @CrossOrigin

    public void exportConsultationData(HttpServletResponse response) throws IOException {
        consultationService.exportConsultationData(String.valueOf(response));
    }

    @PostMapping("/sendReminder")
    @CrossOrigin

    public ResponseEntity<Void> sendReminderEmails() {
        consultationService.sendReminderEmails();
        return new ResponseEntity<>(HttpStatus.OK);

    }
//POST http://localhost:8082/consultations/sendReminder
}


