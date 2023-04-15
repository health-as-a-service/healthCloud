package esprit.etudiant.tn.healthcloud.services;

import esprit.etudiant.tn.healthcloud.entities.Donateur;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface IdonateurService {
    List<Donateur> getAllDonateurs();
    Optional<Donateur> getDonateurById(int id);
    Donateur addDonateur(int idBanque,Donateur donateur) throws ChangeSetPersister.NotFoundException;
    Donateur updateDonateur(Donateur donateur);
    void deleteDonateurById(int id);
}
