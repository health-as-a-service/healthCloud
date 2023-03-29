package tn.esprit.healthcloud.services;

import org.springframework.stereotype.Service;
import tn.esprit.healthcloud.entities.Infirmier;
import tn.esprit.healthcloud.repositories.InfirmierRepository;
import tn.esprit.healthcloud.repositories.InfirmierRepository;

import java.util.List;

@Service
public class InfirmierService implements IInfirmierService {
    InfirmierRepository infirmierRepository;

    public InfirmierService(InfirmierRepository infirmierRepository) {
        this.infirmierRepository = infirmierRepository;
    }

    @Override
    public Infirmier ajouter(Infirmier infirmier) {
        return infirmierRepository.save(infirmier);
    }

    @Override
    public Infirmier modifier(Infirmier infirmier, int id) {
        infirmierRepository.findById(id).orElse(null);
        return infirmierRepository.save(infirmier);
    }

    @Override
    public List<Infirmier> afficher() {
        return infirmierRepository.findAll();
    }

    @Override
    public Infirmier afficherById(int id) {
        return infirmierRepository.findById(id).orElse(null) ;
    }

    @Override
    public void supprimer(int id) {
        infirmierRepository.deleteById(id);

    }
}