package tn.esprit.healthcloud.services;

import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.healthcloud.entities.Laboratoire;

import java.util.List;

public interface ILaboratoireService {

    Laboratoire addLaboratoire(Laboratoire l);
    Laboratoire updateLaboratoire(Laboratoire laboratoire , int idlabo);

    void deleteLaboratoire(@PathVariable int id);

    List<Laboratoire> getAllLaboratoire();

    Laboratoire getLaboratoire(int id);
}
