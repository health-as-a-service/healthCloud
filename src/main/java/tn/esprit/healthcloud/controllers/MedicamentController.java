package tn.esprit.healthcloud.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.healthcloud.entities.Medicament;
import tn.esprit.healthcloud.repositories.MedicamentRepository;
import tn.esprit.healthcloud.services.IMedicamentService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/Medicament")
public class MedicamentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SampleController.class);
    @Autowired
    private MedicamentRepository medicamentRepository;
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
    @PutMapping("/{id}/stock")
    public ResponseEntity<?> updateStock(@PathVariable(value = "id") int id,
                                         @RequestParam(value = "stock") long stock) {
        Optional<Medicament> medicament = medicamentRepository.findById(id);
        if (medicament.isPresent()) {
            medicamentRepository.updateStock(id, stock);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
