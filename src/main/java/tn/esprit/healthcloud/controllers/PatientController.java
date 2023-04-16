package tn.esprit.healthcloud.controllers;

import org.springframework.web.bind.annotation.*;
import tn.esprit.healthcloud.entities.Patient;
import tn.esprit.healthcloud.services.IPatientService;

import java.util.List;

@RestController
public class PatientController {

    IPatientService iPatientService;

    public PatientController(IPatientService iPatientService) {
        this.iPatientService = iPatientService;
    }
    @PostMapping("/patient")
    public Patient ajouterPatient(@RequestBody Patient patient) {
        return iPatientService.ajouterPatient(patient);
    }
    @PutMapping("/patient")
    public Patient modifierPatient(@RequestBody Patient patient) {
        return iPatientService.modifierPatient(patient);
    }
    @GetMapping("/patient")
    public List<Patient> afficherPatients() {
        return iPatientService.afficherPatients();
    }
    @GetMapping("/patient/{id}")
    public Patient afficherPatient(@PathVariable("id") int id) {
        return iPatientService.afficherPatient(id);
    }
    @DeleteMapping("/patient/{id}")
    public void supprimerPatient(@PathVariable("id")  int id) {
        iPatientService.supprimerPatient(id);
    }

    @PutMapping("/assign/{idP}/{idD}")
    public Patient assignerDossierToPatient(@PathVariable("idP")Integer idP, @PathVariable("idD")Integer idD) {
        return iPatientService.assignerDossierToPatient(idP, idD);
    }
}
