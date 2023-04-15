package tn.esprit.healthcloud.Service;

import tn.esprit.healthcloud.Entities.Donateur;
import tn.esprit.healthcloud.Entities.TypeSanguin;
import tn.esprit.healthcloud.Repositories.DonateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
public class DonateurService implements IserviceDonateur {

    private DonateurRepository donateurRepository;
    private BanqueDeSangService banqueDeSangService;

    @Autowired
    public DonateurService(DonateurRepository donateurRepository, BanqueDeSangService banqueDeSangService) {
        this.donateurRepository = donateurRepository;
        this.banqueDeSangService = banqueDeSangService;
    }

    @Override
    public Donateur ajouterDonateur(Donateur donateur) {
        donateur.setDernierDon(new Date());
        Donateur nouveauDonnateur = donateurRepository.save(donateur);
        banqueDeSangService.augmenterQuantiteSang(nouveauDonnateur.getTypeSanguin(), nouveauDonnateur.getQuantitéTotale());
        return nouveauDonnateur;
    }

    @Override
    public Donateur modifierDonateur(Long idDonateur, Donateur donateur) {
        Donateur existingDonateur = donateurRepository.findById(idDonateur)
                .orElseThrow(() -> new EntityNotFoundException("Donateur with id " + idDonateur + " not found"));

        existingDonateur.setNomDonateur(donateur.getNomDonateur());
        existingDonateur.setPrenomDonateur(donateur.getPrenomDonateur());
        existingDonateur.setAdresse(donateur.getAdresse());
        existingDonateur.setNumeroTelephone(donateur.getNumeroTelephone());
        existingDonateur.setQuantitéTotale(donateur.getQuantitéTotale());
        existingDonateur.setDernierDon(donateur.getDernierDon());

        return donateurRepository.save(existingDonateur);
    }

    @Override
    public Donateur AfficherDonateurById(Long idDonateur) {
        return donateurRepository.findById(idDonateur)
                .orElseThrow(() -> new EntityNotFoundException("Donateur with id " + idDonateur + " not found"));
    }

    @Override
    public List<Donateur> afficherDonateurs() {
        return donateurRepository.findAll();
    }

    @Override
    public void supprimerDonateur(Long idDonateur) {
        donateurRepository.deleteById(idDonateur);
    }

    @Override
    public List<Donateur> getDonateursByTypeSanguin(TypeSanguin typeSanguin) {
        return donateurRepository.findByTypeSanguinAndQuantitéTotaleGreaterThan(typeSanguin, 0);
    }
}
