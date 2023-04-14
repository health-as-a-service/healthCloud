package esprit.etudiant.tn.healthcloud.controllers;

import esprit.etudiant.tn.healthcloud.entities.BanqueSang;
import esprit.etudiant.tn.healthcloud.services.BanqueSangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/banquesang")
public class BanqueSangController {

    @Autowired
    private BanqueSangService banqueSangService;

    @GetMapping
    public ResponseEntity<List<BanqueSang>> getAllBanqueSangs() {
        List<BanqueSang> banqueSangs = banqueSangService.getAllBanqueSangs();
        return new ResponseEntity<>(banqueSangs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BanqueSang> getBanqueSangById(@PathVariable int id) {
        Optional<BanqueSang> banqueSang = banqueSangService.getBanqueSangById(id);
        if (banqueSang.isPresent()) {
            return new ResponseEntity<>(banqueSang.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<BanqueSang> addBanqueSang(@RequestBody BanqueSang banqueSang) {
        BanqueSang newBanqueSang = banqueSangService.addBanqueSang(banqueSang);
        return new ResponseEntity<>(newBanqueSang, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BanqueSang> updateBanqueSang(@PathVariable int id, @RequestBody BanqueSang banqueSang) {
        Optional<BanqueSang> existingBanqueSang = banqueSangService.getBanqueSangById(id);
        if (existingBanqueSang.isPresent()) {
            banqueSang.setIdBanqueSang(id);
            BanqueSang updatedBanqueSang = banqueSangService.updateBanqueSang(banqueSang);
            return new ResponseEntity<>(updatedBanqueSang, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBanqueSangById(@PathVariable int id) {
        Optional<BanqueSang> banqueSang = banqueSangService.getBanqueSangById(id);
        if (banqueSang.isPresent()) {
            banqueSangService.deleteBanqueSangById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
