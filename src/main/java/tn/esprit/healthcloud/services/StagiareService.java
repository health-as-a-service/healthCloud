package tn.esprit.healthcloud.services;

import org.springframework.stereotype.Service;
import tn.esprit.healthcloud.entities.Stagiare;
import tn.esprit.healthcloud.repositories.StagiareRepository;

import java.util.List;

@Service
public class StagiareService implements IStagiareService{
    StagiareRepository stagiareRepository;

    public StagiareService(StagiareRepository stagiareRepository) {
        this.stagiareRepository = stagiareRepository;
    }

    @Override
    public Stagiare ajouter(Stagiare stagiare) {
        return stagiareRepository.save(stagiare);
    }

    @Override
    public Stagiare modifier(Stagiare stagiare, int id) {
        stagiareRepository.findById(id).orElse(null);
        return stagiareRepository.save(stagiare);
    }

    @Override
    public List<Stagiare> afficher() {
        return null;
    }

    @Override
    public Stagiare afficherById(int id) {
        return null;
    }

    @Override
    public void supprimer(int id) {

    }
}