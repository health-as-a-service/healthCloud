package tn.esprit.healthcloud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.healthcloud.entities.Medicament;
import tn.esprit.healthcloud.repositories.MedicamentRepository;

import java.util.List;
@Service
public class MedicamentService implements IMedicamentService{
    @Autowired
    MedicamentRepository medicamentRepository;
    @Override
    public Medicament addMedicament(Medicament m)
    {
        Medicament medicament =medicamentRepository.save(m);
        return medicament;
    }
    @Override
    public Medicament updateMedicament(Medicament medicament , int idmedicament) {
        Medicament med = medicamentRepository.findById(idmedicament).get();
        medicament=med;
        medicamentRepository.save(medicament);
        return medicament;
    }
    @Override
    public void deleteMedicament(@PathVariable int id) {
        medicamentRepository.deleteById(id);
    }


    @Override
    public List<Medicament> getAllMedicament() {
        return (List<Medicament>) medicamentRepository.findAll();
    }


    @Override
    public Medicament getMedicament(int id) {
        Medicament medicament = medicamentRepository.findById(id).orElse(null);
        return medicament;
    }
}
