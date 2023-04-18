package tn.esprit.healthcloud.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.healthcloud.entities.Medicament;
import tn.esprit.healthcloud.services.IMedicamentService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Medicament")
public class MedicamentController {
    @Autowired
    IMedicamentService medicamentService;
    @PostMapping("/add-medicament")
    public Medicament addMedicament(@RequestBody Medicament m) {
        Medicament medicament = medicamentService.addMedicament(m);
        return medicament;
    }


    @DeleteMapping("/delete-medicament/{id-medicament}")
    public void deletePharmacie(@PathVariable("id-medicament") int id)
    {
        medicamentService.deleteMedicament(id);
    }

    @GetMapping("/getMedicament/{id}")
    public Medicament getPharmacieById(@PathVariable int id)
    {
        return medicamentService.getMedicament(id);
    }
    @GetMapping("/getAllMedicament")
    public List<Medicament> getAllMedicament()
    {
        return medicamentService.getAllMedicament();
    }
    @PutMapping("/updatePharmacie/{idPharmacie}")
    public Medicament updatePharmacie(@PathVariable("idPharmacie") int id_medicament, @RequestBody Medicament medicament)
    {
        return medicamentService.updateMedicament(medicament,id_medicament);
    }
}
