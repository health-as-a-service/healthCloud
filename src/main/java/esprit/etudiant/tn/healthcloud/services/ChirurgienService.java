package esprit.etudiant.tn.healthcloud.services;

import esprit.etudiant.tn.healthcloud.entities.Chirurgien;
import esprit.etudiant.tn.healthcloud.repositories.ChirurgienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChirurgienService implements IChirurgienService {

    @Autowired
    private ChirurgienRepository chirurgienRepository;

    @Override
    public List<Chirurgien> findAllChirurgiens() {
        return chirurgienRepository.findAll();
    }

    @Override
    public Chirurgien findChirurgienById(int id) {
        return chirurgienRepository.findById(id).orElse(null);
    }

    @Override
    public void addChirurgien(Chirurgien chirurgien) {
        chirurgienRepository.save(chirurgien);
    }

    @Override
    public void updateChirurgien(int id, Chirurgien chirurgien) {
        Chirurgien existingChirurgien = chirurgienRepository.findById(id).orElse(null);
        if (existingChirurgien != null) {
            existingChirurgien.setNom(chirurgien.getNom());
            existingChirurgien.setPrenom(chirurgien.getPrenom());
            chirurgienRepository.save(existingChirurgien);
        }
    }

    @Override
    public void deleteChirurgien(int id) {
        chirurgienRepository.deleteById(id);
    }
}
