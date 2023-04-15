package tn.esprit.healthcloud.Controller;

import tn.esprit.healthcloud.Entities.BanqueSang;
import tn.esprit.healthcloud.Entities.TypeSanguin;
import tn.esprit.healthcloud.Service.IserviceBanque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/banque-sang")
public class BanqueSangController {

    @Autowired
    IserviceBanque iserviceBanque;

    @GetMapping("/all")
    public List<BanqueSang> getAll() {
        return iserviceBanque.afficherBanque();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BanqueSang> getById(@PathVariable int id) {
        BanqueSang banqueSang = iserviceBanque.afficherBanqueById(id);
        if (banqueSang != null) {
            return ResponseEntity.ok(banqueSang);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<BanqueSang> addBanqueSang(@RequestBody BanqueSang banqueSang) {
        BanqueSang newBanqueSang = iserviceBanque.ajouterBanque(banqueSang);
        return ResponseEntity.ok(newBanqueSang);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BanqueSang> updateBanqueSang(@PathVariable int id, @RequestBody BanqueSang banqueSang) {
        BanqueSang updatedBanqueSang = iserviceBanque.modifierBanque(id, banqueSang);
        if (updatedBanqueSang != null) {
            return ResponseEntity.ok(updatedBanqueSang);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBanqueSang(@PathVariable int id) {
        iserviceBanque.supprimerBanque(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/augmenter-quantite/{type}/{quantite}")
    public ResponseEntity<Void> augmenterQuantiteSang(@PathVariable TypeSanguin type, @PathVariable int quantite) {
        iserviceBanque.augmenterQuantiteSang(type, quantite);
        return ResponseEntity.ok().build();
    }

}
