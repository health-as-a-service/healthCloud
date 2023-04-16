package tn.esprit.healthcloud.services;

import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.healthcloud.entities.Pharmacien;

import java.util.List;

public interface IPharmacienService {
    Pharmacien addPharmacien(Pharmacien t);
    Pharmacien updatePharmacien(Pharmacien pharmacien , int idpharmacien);
    void deletePharmacien(@PathVariable int id);
    List<Pharmacien> getAllPharmacie();
    Pharmacien getPharmacien(int id);
}
