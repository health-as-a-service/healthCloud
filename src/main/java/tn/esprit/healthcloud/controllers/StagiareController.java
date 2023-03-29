package tn.esprit.healthcloud.controllers;

import org.springframework.web.bind.annotation.*;
import tn.esprit.healthcloud.entities.Stagiare;
import tn.esprit.healthcloud.services.IStagiareService;

import java.util.List;
@RestController
public class StagiareController {
    IStagiareService iStagiareService;

    public StagiareController(IStagiareService iStagiareService) {
        this.iStagiareService = iStagiareService;
    }
    @PostMapping("/Stagiare")
    Stagiare addAdmin(@RequestBody Stagiare stagiare){
        return iStagiareService.ajouter(stagiare);
    }
    @PutMapping("/Stagiare/{id}")
    Stagiare modifierAdmin(@RequestBody Stagiare stagiare, @PathVariable int id){
        return iStagiareService.modifier(stagiare,id);
    }
    @GetMapping("/Stagiare")
    List<Stagiare> afficherAdmin(){
        return iStagiareService.afficher();
    }
    @GetMapping("/Stagiare/{id}")
    Stagiare afficherAdminById(@PathVariable int id){
        return iStagiareService.afficherById(id);
    }
    @DeleteMapping("/Stagiare/{id}")
    void supprimeradmin(@PathVariable int id){
        iStagiareService.supprimer(id);
    }
}
