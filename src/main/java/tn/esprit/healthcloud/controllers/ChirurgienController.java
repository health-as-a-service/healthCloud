package tn.esprit.healthcloud.controllers;

import org.springframework.web.bind.annotation.*;
import tn.esprit.healthcloud.entities.Chirurgien;
import tn.esprit.healthcloud.services.IChirurgienService;

import java.util.List;

@RestController
public class ChirurgienController {
    IChirurgienService iChirurgienService;

    public ChirurgienController(IChirurgienService iChirurgienService) {
        this.iChirurgienService = iChirurgienService;
    }
    @PostMapping("/Chirurgien")
    Chirurgien addAdmin(@RequestBody Chirurgien chirurgien){
        return iChirurgienService.ajouter(chirurgien);
    }
    @PutMapping("/Chirurgien/{id}")
    Chirurgien modifierAdmin(@RequestBody Chirurgien chirurgien, @PathVariable int id){
        return iChirurgienService.modifier(chirurgien,id);
    }
    @GetMapping("/Chirurgien")
    List<Chirurgien> afficherAdmin(){
        return iChirurgienService.afficher();
    }
    @GetMapping("/Chirurgien/{id}")
    Chirurgien afficherAdminById(@PathVariable int id){
        return iChirurgienService.afficherById(id);
    }
    @DeleteMapping("/Chirurgien/{id}")
    void supprimeradmin(@PathVariable int id){
        iChirurgienService.supprimer(id);
    }
}