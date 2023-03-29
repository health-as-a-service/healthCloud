package tn.esprit.healthcloud.controllers;

import org.springframework.web.bind.annotation.*;
import tn.esprit.healthcloud.entities.Medecin;
import tn.esprit.healthcloud.services.IMedecinService;

import java.util.List;

@RestController
public class MedecinController {
    IMedecinService iMedecinService;

    public MedecinController(IMedecinService iMedecinService) {
        this.iMedecinService = iMedecinService;
    }
    @PostMapping("/Medecin")
    Medecin addAdmin(@RequestBody Medecin medecin){
        return iMedecinService.ajouter(medecin);
    }
    @PutMapping("/Medecin/{id}")
    Medecin modifierAdmin(@RequestBody Medecin medecin, @PathVariable int id){
        return iMedecinService.modifier(medecin,id);
    }
    @GetMapping("/Medecin")
    List<Medecin> afficherAdmin(){
        return iMedecinService.afficher();
    }
    @GetMapping("/Medecin/{id}")
    Medecin afficherAdminById(@PathVariable int id){
        return iMedecinService.afficherById(id);
    }
    @DeleteMapping("/Medecin/{id}")
    void supprimeradmin(@PathVariable int id){
        iMedecinService.supprimer(id);
    }
}
