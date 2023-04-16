package tn.esprit.healthcloud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.healthcloud.entities.Pharmacie;
import tn.esprit.healthcloud.entities.Pharmacien;
import tn.esprit.healthcloud.repositories.PharmacienRepository;

import java.util.List;

@Service
public class PharmacienService implements IPharmacienService{

    @Autowired
    PharmacienRepository pharmacienRepository;

    @Override
    public Pharmacien addPharmacien(Pharmacien t)
    {
        Pharmacien pharmacien = pharmacienRepository.save(t);
        return pharmacien;
    }
    @Override
    public Pharmacien updatePharmacien(Pharmacien pharmacien , int idpharmacien) {
        Pharmacien pharn = pharmacienRepository.findById(idpharmacien).get();
        pharmacien=pharn;
        pharmacienRepository.save(pharmacien);
        return pharmacien;
    }
    @Override
    public void deletePharmacien(@PathVariable int id) {
        pharmacienRepository.deleteById(id);
    }


    @Override
    public List<Pharmacien> getAllPharmacie() {
        return (List<Pharmacien>) pharmacienRepository.findAll();
    }


    @Override
    public Pharmacien getPharmacien(int id) {
        Pharmacien pharmacien = pharmacienRepository.findById(id).get();
        return pharmacien;
    }
}
