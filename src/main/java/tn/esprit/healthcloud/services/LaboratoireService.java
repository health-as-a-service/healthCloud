package tn.esprit.healthcloud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.healthcloud.entities.Laboratoire;
import tn.esprit.healthcloud.repositories.LaboRepository;

import java.util.List;

@Service
public class LaboratoireService implements ILaboratoireService {

    @Autowired
    LaboRepository LaboRepository;
    @Override
    public Laboratoire addLaboratoire(Laboratoire l)
    {
        Laboratoire laboratoire =LaboRepository.save(l);
        return laboratoire;
    }
    @Override
    public Laboratoire updateLaboratoire(Laboratoire laboratoire , int idlabo) {
        Laboratoire labor = LaboRepository.findById(idlabo).get();
        laboratoire=labor;
        LaboRepository.save(laboratoire);
        return laboratoire;
    }
    @Override
    public void deleteLaboratoire(@PathVariable int id) {
        LaboRepository.deleteById(id);
    }


    @Override
    public List<Laboratoire> getAllLaboratoire() {
        return (List<Laboratoire>) LaboRepository.findAll();
    }


    @Override
    public Laboratoire getLaboratoire(int id) {
        Laboratoire laboratoire = LaboRepository.findById(id).orElse(null);
        return laboratoire;
    }
}
