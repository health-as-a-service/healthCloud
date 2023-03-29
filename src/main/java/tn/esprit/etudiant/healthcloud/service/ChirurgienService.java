package tn.esprit.etudiant.healthcloud.service;

import org.springframework.stereotype.Service;
import tn.esprit.etudiant.healthcloud.entities.Chirurgien;
import tn.esprit.etudiant.healthcloud.repository.ChirurgienRepository;

import java.util.List;
@Service
public class ChirurgienService implements IChirurgienService {
    ChirurgienRepository chirurgienRepository;

    public ChirurgienService(ChirurgienRepository chirurgienRepository) {
        this.chirurgienRepository = chirurgienRepository;
    }

    @Override
    public Chirurgien ajouter(Chirurgien chirurgien) {
        return chirurgienRepository.save(chirurgien);
    }

    @Override
    public Chirurgien modifier(Chirurgien chirurgien, int id) {
        chirurgienRepository.findById(id).orElse(null);
        return chirurgienRepository.save(chirurgien);
    }

    @Override
    public List<Chirurgien> afficher() {
        return chirurgienRepository.findAll();
    }

    @Override
    public Chirurgien afficherById(int id) {
        return chirurgienRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimer(int id) {
        chirurgienRepository.deleteById(id);

    }
}