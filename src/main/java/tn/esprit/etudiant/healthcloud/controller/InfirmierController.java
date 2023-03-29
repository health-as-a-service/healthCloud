package tn.esprit.etudiant.healthcloud.controller;

import org.springframework.web.bind.annotation.*;
import tn.esprit.etudiant.healthcloud.entities.Infirmier;
import tn.esprit.etudiant.healthcloud.service.IInfirmierService;

import java.util.List;

@RestController
public class InfirmierController {
    IInfirmierService iInfirmierService;

    public InfirmierController(IInfirmierService iInfirmierService) {
        this.iInfirmierService = iInfirmierService;
    }
    @PostMapping("/Infirmier")
    Infirmier addAdmin(@RequestBody Infirmier infirmier){
        return iInfirmierService.ajouter(infirmier);
    }
    @PutMapping("/Infirmier/{id}")
    Infirmier modifierAdmin(@RequestBody Infirmier infirmier, @PathVariable int id){
        return iInfirmierService.modifier(infirmier,id);
    }
    @GetMapping("/Infirmier")
    List<Infirmier> afficherAdmin(){
        return iInfirmierService.afficher();
    }
    @GetMapping("/Infirmier/{id}")
    Infirmier afficherAdminById(@PathVariable int id){
        return iInfirmierService.afficherById(id);
    }
    @DeleteMapping("/Infirmier/{id}")
    void supprimeradmin(@PathVariable int id){
        iInfirmierService.supprimer(id);
    }
}
