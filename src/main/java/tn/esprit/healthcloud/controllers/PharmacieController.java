package tn.esprit.healthcloud.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.healthcloud.entities.Pharmacie;
import tn.esprit.healthcloud.services.IPharmacieService;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/Pharmacie")
public class PharmacieController {
    @Autowired
    IPharmacieService pharmacieService;
    @PostMapping("/add-pharmacie")
    public Pharmacie addPharmacie(@RequestBody Pharmacie p) {
        Pharmacie pharmacie = pharmacieService.addPharmacie(p);
        return pharmacie;
    }


    @DeleteMapping("/delete-pharmacie/{id-pharmacie}")
    public void deletePharmacie(@PathVariable("id-pharmacie") int id)
    {
        pharmacieService.deletePharmacie(id);
    }

    @GetMapping("/getPharmacie/{id}")
        public Pharmacie getPharmacieById(@PathVariable int id)
        {
            return pharmacieService.getPharmacie(id);
        }
    @GetMapping("/getAllPharmacie")
    public List<Pharmacie> getAllPharmacie()
    {
        return pharmacieService.getAllPharmacie();
    }
        @PutMapping("/updatePharmacie/{idPharmacie}")
        public Pharmacie updatePharmacie(@PathVariable("idPharmacie") int id_pharmacie, @RequestBody Pharmacie pharmacie)
        {
            return pharmacieService.updatePharmacie(pharmacie,id_pharmacie);
        }
}
