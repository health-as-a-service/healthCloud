package tn.esprit.healthcloud.Controller;

import tn.esprit.healthcloud.Entities.Donateur;
import esprit.etudiant.tn.healthcloud.ntities.TypeSanguin;
import esprit.etudiant.tn.healthcloud.service.DonateurService;
import esprit.etudiant.tn.healthcloud.service.IServiceDonateur;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donateurs")
public class DonateurController {

    private IServiceDonateur iServiceDonateur;

    public DonateurController(IServiceDonateur iServiceDonateur) {
        this.iServiceDonateur = iServiceDonateur;
    }

    @PostMapping("/ajouter")
    public ResponseEntity<Donateur> ajouterDonateur(@RequestBody Donateur donateur) {
        Donateur nouveauDonateur = iServiceDonateur.ajouterDonateur(donateur);
        return new ResponseEntity<>(nouveauDonateur, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donateur> modifierDonateur(@PathVariable("id") Long idDonateur, @RequestBody Donateur donateur) {
        Donateur modifierDonateur = iServiceDonateur.modifierDonateur(idDonateur, donateur);
        if (modifierDonateur != null) {
            return ResponseEntity.ok(modifierDonateur);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Donateur> afficherDonateurs() {
        return iServiceDonateur.afficherDonateurs();
    }

    @GetMapping("/{id}")
    public Donateur afficherDonateur(@PathVariable("id") long idDonateur) {
        return iServiceDonateur.afficherDonateurById(idDonateur);
    }

    @DeleteMapping("/{id}")
    public void supprimerDonateur(@PathVariable("id") long idDonateur) {
        iServiceDonateur.supprimerDonateur(idDonateur);
    }

    @GetMapping("/type-sanguin/{typeSanguin}")
    public ResponseEntity<List<Donateur>> getDonateursByTypeSanguin(@PathVariable("typeSanguin") TypeSanguin typeSanguin) {
        List<Donateur> donneurs = iServiceDonateur.getDonateursByTypeSanguin(typeSanguin);
        return new ResponseEntity<>(donneurs, HttpStatus.OK);
    }
}
