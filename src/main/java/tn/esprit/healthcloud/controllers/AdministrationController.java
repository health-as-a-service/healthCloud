package tn.esprit.healthcloud.controllers;

import org.springframework.web.bind.annotation.*;
import tn.esprit.healthcloud.entities.Administration;
import tn.esprit.healthcloud.services.IAdministrationService;

import java.util.List;

@RestController
public class AdministrationController {
    IAdministrationService iAdministrationService;

    public AdministrationController(IAdministrationService iAdministrationService) {
        this.iAdministrationService = iAdministrationService;
    }
    @PostMapping("/administration")
    Administration addAdmin(@RequestBody Administration administration){
        return iAdministrationService.ajouter(administration);
    }
    @PutMapping("/administration/{id}")
    Administration modifierAdmin(@RequestBody Administration administration, @PathVariable int id){
        return iAdministrationService.modifier(administration,id);
    }
    @GetMapping("/administration")
    List<Administration> afficherAdmin(){
        return iAdministrationService.afficher();
    }
    @GetMapping("/administration/{id}")
    Administration afficherAdminById(@PathVariable int id){
        return iAdministrationService.afficherById(id);
    }
    @DeleteMapping("/administration/{id}")
    void supprimeradmin(@PathVariable int id){
        iAdministrationService.supprimer(id);
    }
}