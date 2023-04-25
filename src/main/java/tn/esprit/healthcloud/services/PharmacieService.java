package tn.esprit.healthcloud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.healthcloud.entities.Pharmacie;
import tn.esprit.healthcloud.repositories.PharmacieRepository;

import java.util.List;

@Service
public class PharmacieService implements IPharmacieService{
    @Autowired
    PharmacieRepository pharmacieRepository;
    @Override
    public Pharmacie addPharmacie(Pharmacie t)
    {
        Pharmacie pharmacie =pharmacieRepository.save(t);
        return pharmacie;
    }
    @Override
    public Pharmacie updatePharmacie(Pharmacie pharmacie , int idpharmacie) {
        Pharmacie phar = pharmacieRepository.findById(idpharmacie).get();
        pharmacie=phar;
        pharmacieRepository.save(pharmacie);
        return pharmacie;
    }
    @Override
    public void deletePharmacie(@PathVariable int id) {
        pharmacieRepository.deleteById(id);
    }


    @Override
    public List<Pharmacie> getAllPharmacie() {
        return (List<Pharmacie>) pharmacieRepository.findAll();
    }


    @Override
    public Pharmacie getPharmacie(int id) {
        Pharmacie pharmacie = pharmacieRepository.findById(id).orElse(null);
        return pharmacie;
    }
}
