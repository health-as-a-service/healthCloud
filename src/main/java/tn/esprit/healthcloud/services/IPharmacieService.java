package tn.esprit.healthcloud.services;

import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.healthcloud.entities.Pharmacie;

import java.util.List;

public interface IPharmacieService {
    Pharmacie addPharmacie(Pharmacie t);
    Pharmacie updatePharmacie(Pharmacie pharmacie , int idpharmacie);
    void deletePharmacie(@PathVariable int id);
    List<Pharmacie> getAllPharmacie();
    Pharmacie getPharmacie(int id);

}
