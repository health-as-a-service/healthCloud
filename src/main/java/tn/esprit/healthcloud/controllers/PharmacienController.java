package tn.esprit.healthcloud.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.healthcloud.entities.Pharmacien;
import tn.esprit.healthcloud.services.IPharmacienService;
import tn.esprit.healthcloud.services.PharmacienService;

@RestController
@AllArgsConstructor
@RequestMapping("/Pharmacien")
public class PharmacienController {
    @Autowired
    IPharmacienService pharmacienService;
    @PostMapping("/add-pharmacie")
    public Pharmacien addPharmacien(@RequestBody Pharmacien pn) {
        Pharmacien pharmacien = pharmacienService.addPharmacien(pn);
        return pharmacien;
    }


    @DeleteMapping("/delete-pharmacien/{id-pharmacien}")
    public void deletePharmacien(@PathVariable("id-pharmacien") int id)
    {
        pharmacienService.deletePharmacien(id);
    }

    @GetMapping("/getAllPharmacien")
    public Pharmacien getPharmacienById(@PathVariable("id-pharmacien") int id)
    {
        return pharmacienService.getPharmacien(id);
    }
    @PutMapping("/updatePharmacien/{idPharmacien}")
    public Pharmacien updatePharmacien(@PathVariable("idPharmacie") int id_pharmacien, @RequestBody Pharmacien pharmacien)
    {
        return pharmacienService.updatePharmacien(pharmacien,id_pharmacien);
    }
}
