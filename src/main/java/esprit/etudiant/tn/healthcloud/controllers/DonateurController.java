package esprit.etudiant.tn.healthcloud.controllers;

import esprit.etudiant.tn.healthcloud.entities.BanqueSang;
import esprit.etudiant.tn.healthcloud.entities.Donateur;
import esprit.etudiant.tn.healthcloud.repositories.BanqueSangRepository;
import esprit.etudiant.tn.healthcloud.repositories.DonateurRepository;
import esprit.etudiant.tn.healthcloud.services.BanqueSangService;
import esprit.etudiant.tn.healthcloud.services.IdonateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/donateurs")
public class DonateurController {

    @Autowired
    private IdonateurService donateurService;
    @Autowired
    private BanqueSangRepository banqueSangRepository;
    @Autowired
    private DonateurRepository donateurRepository;
    @Autowired
    private BanqueSangService banqueSangService;

    @GetMapping
    public ResponseEntity<List<Donateur>> getAllDonateurs() {
        List<Donateur> donateurs = donateurService.getAllDonateurs();
        if (donateurs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(donateurs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donateur> getDonateurById(@PathVariable("id") int id) {
        Optional<Donateur> donateur = donateurService.getDonateurById(id);
        return donateur.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/banques/{idBanque}/donateurs")
    public ResponseEntity<Donateur> addDonateur(@PathVariable int idBanque, @RequestBody Donateur donateur) throws ChangeSetPersister.NotFoundException {
        BanqueSang banqueSang = banqueSangService.getBanqueSangById(idBanque).orElse(null);
        donateur.setBanqueSang(banqueSang);
        banqueSang.getDonateurs().add(donateur);
        banqueSangService.updateBanqueSang(banqueSang);
        Donateur newDonateur = donateurService.addDonateur(idBanque, donateur);
        return ResponseEntity.ok(newDonateur);
    }









    @PutMapping("/{id}")
    public ResponseEntity<Donateur> updateDonateur(@PathVariable("id") int id, @RequestBody Donateur donateur) {
        Optional<Donateur> donateurData = donateurService.getDonateurById(id);
        if (donateurData.isPresent()) {
            Donateur updatedDonateur = donateurData.get();
            updatedDonateur.setNomDonateur(donateur.getNomDonateur());
            updatedDonateur.setPrenomDonateur(donateur.getPrenomDonateur());
            updatedDonateur.setAdresse(donateur.getAdresse());
            updatedDonateur.setNumeroTelephone(donateur.getNumeroTelephone());
            updatedDonateur.setTypeSanguin(donateur.getTypeSanguin());
            updatedDonateur.setQuantiteTotale(donateur.getQuantiteTotale());
            updatedDonateur.setDernierDon(donateur.getDernierDon());
            updatedDonateur.setBanqueSang(donateur.getBanqueSang());
            return new ResponseEntity<>(donateurService.updateDonateur(updatedDonateur), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDonateur(@PathVariable("id") int id) {
        try {
            donateurService.deleteDonateurById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
