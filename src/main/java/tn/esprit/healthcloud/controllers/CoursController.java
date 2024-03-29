package tn.esprit.healthcloud.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.healthcloud.entities.Cours;
import tn.esprit.healthcloud.entities.User;
import tn.esprit.healthcloud.repositories.UserRepository;
import tn.esprit.healthcloud.services.CoursService;

import java.util.List;

@RestController
@RequestMapping("/api/cours")
@AllArgsConstructor

public class CoursController {
    private CoursService coursService;

    private UserRepository userRepository;



    @GetMapping("")
    public ResponseEntity<List<Cours>> getCours() {
        List<Cours> coursList = coursService.getAllCours();
        return ResponseEntity.status(HttpStatus.FOUND).body(coursList);
    }

    @PostMapping("")
    public ResponseEntity<Cours> saveCours(@RequestBody Cours cours) {
        Cours savedCours = coursService.saveCours(cours);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCours);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cours> getCoursById(@PathVariable("id") int id) {
        Cours cours = coursService.getCoursById(id);
        if (cours != null) {
            return ResponseEntity.ok(cours);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCours(@PathVariable("id") int id) {
        coursService.deleteCours(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Cours>> getCoursByDoctorId(@PathVariable("doctorId") long doctorId) {
        List<Cours> coursList = coursService.getCoursByDoctorId(doctorId);
        if (coursList != null) {
            return ResponseEntity.ok(coursList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/stagiaire/{stagiaireId}")
    public ResponseEntity<List<Cours>> getCoursByStagiaireId(@PathVariable("stagiaireId") long stagiaireId) {
        List<Cours> coursList = coursService.getCoursByStagiaireId(stagiaireId);
        if (coursList != null) {
            return ResponseEntity.ok(coursList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{coursId}/stagiaire/{stagiaireId}")
    public ResponseEntity<Void> addStagiaireToCours(@PathVariable("coursId") int coursId, @PathVariable("stagiaireId") long stagiaireId) {
        coursService.addStagiaireToCours(coursId, stagiaireId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{coursId}/stagiaire/{stagiaireId}")
    public ResponseEntity<Void> removeStagiaireFromCours(@PathVariable("coursId") int coursId, @PathVariable("stagiaireId") long stagiaireId) {
        coursService.removeStagiaireFromCours(coursId, stagiaireId);
        return ResponseEntity.ok().build();
    }
}
