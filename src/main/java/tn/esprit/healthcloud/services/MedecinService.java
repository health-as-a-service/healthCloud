package tn.esprit.healthcloud.services;

import org.springframework.stereotype.Service;
import tn.esprit.healthcloud.entities.Medecin;
import tn.esprit.etudiant.healthcloud.repository.MedecinRepositrory;
import tn.esprit.healthcloud.repositories.MedecinRepositrory;

import java.util.List;

@Service
public class MedecinService implements IMedecinService{
    MedecinRepositrory medecinRepositrory;

    public MedecinService(MedecinRepositrory medecinRepositrory) {
        this.medecinRepositrory = medecinRepositrory;
    }

    @Override
    public Medecin ajouter(Medecin medecin) {
        return medecinRepositrory.save(medecin);
    }

    @Override
    public Medecin modifier(Medecin medecin, int id) {
        medecinRepositrory.findById(id).orElse(null);
        return medecinRepositrory.save(medecin);
    }

    @Override
    public List<Medecin> afficher() {
        return medecinRepositrory.findAll();
    }

    @Override
    public Medecin afficherById(int id) {
        return medecinRepositrory.findById(id).orElse(null);
    }

    @Override
    public void supprimer(int id) {
        medecinRepositrory.deleteById(id);
    }
}
