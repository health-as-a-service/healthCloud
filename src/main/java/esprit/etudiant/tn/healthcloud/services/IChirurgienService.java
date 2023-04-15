package esprit.etudiant.tn.healthcloud.services;

import esprit.etudiant.tn.healthcloud.entities.Chirurgien;

import java.util.List;

public interface IChirurgienService {
    List<Chirurgien> findAllChirurgiens();

    Chirurgien findChirurgienById(int id);

    void addChirurgien(Chirurgien chirurgien);

    void updateChirurgien(int id, Chirurgien chirurgien);

    void deleteChirurgien(int id);
}
