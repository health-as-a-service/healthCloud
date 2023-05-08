package tn.esprit.healthcloud.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.healthcloud.entities.Laboratoire;
import tn.esprit.healthcloud.services.ILaboratoireService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Laboratoire")
public class LaboratoireController {
    @Autowired
    ILaboratoireService laboratoireService;
    @PostMapping("/add-laboratoire")
    public Laboratoire addLaboratoire(@RequestBody Laboratoire l) {
        Laboratoire laboratoire = laboratoireService.addLaboratoire(l);
        return laboratoire;
    }


    @DeleteMapping("/delete-laboratoire/{id-laboratoire}")
    public void deleteLaboratoire(@PathVariable("id-laboratoire") int id)
    {
        laboratoireService.deleteLaboratoire(id);
    }

    @GetMapping("/getLaboratoire/{id}")
    public Laboratoire getLaboratoireById(@PathVariable int id)
    {
        return laboratoireService.getLaboratoire(id);
    }

    @GetMapping("/getAllLaboratoire")
    public List<Laboratoire> getAllLaboratoire()
    {
        return laboratoireService.getAllLaboratoire();
    }
    @PutMapping("/updateLaboratoire/{idLaboratoire}")
    public Laboratoire updateLaboratoire(@PathVariable("idLaboratoire") int id_laboratoire, @RequestBody Laboratoire laboratoire)
    {
        return laboratoireService.updateLaboratoire(laboratoire,id_laboratoire);
    }
}
