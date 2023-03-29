package tn.esprit.healthcloud.services;

import org.springframework.stereotype.Service;
import tn.esprit.healthcloud.entities.Administration;
import tn.esprit.healthcloud.repositories.AdministrationRepository;

import java.util.List;

@Service
public class AdministrationService implements IAdministrationService {
    AdministrationRepository administrationRepository;

    public AdministrationService(AdministrationRepository administrationRepository) {
        this.administrationRepository = administrationRepository;
    }

    @Override
    public Administration ajouter(Administration administration) {
        return administrationRepository.save(administration);
    }

    @Override
    public Administration modifier(Administration administration, int id) {
        administrationRepository.findById(id).orElse(null);
        return administrationRepository.save(administration);

    }

    @Override
    public List<Administration> afficher() {
        return administrationRepository.findAll();
    }

    @Override
    public Administration afficherById(int id) {
        return administrationRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimer(int id) {
        administrationRepository.deleteById(id);

    }
}