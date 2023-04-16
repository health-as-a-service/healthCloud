package tn.esprit.healthcloud.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.healthcloud.entities.DossierMedical;
import tn.esprit.healthcloud.repositories.DossierMRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class DossierMService implements IDossierMService {

    DossierMRepository dossierMRepository;


    @Override
    public DossierMedical ajouterDMedical(DossierMedical dossierMedical) {
        return dossierMRepository.save(dossierMedical);
    }

    @Override
    public DossierMedical modifierDMedical(DossierMedical dossierMedical) {
        return dossierMRepository.save(dossierMedical);

    }

    @Override
    public List<DossierMedical> afficherDMedicaux() {
        return dossierMRepository.findAll();
    }

    @Override
    public DossierMedical afficherDMedical(int id) {
        return dossierMRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimerDMedical(int id) {
        dossierMRepository.deleteById(id);

    }
}

