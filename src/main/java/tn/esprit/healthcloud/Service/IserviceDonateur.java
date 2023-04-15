package tn.esprit.healthcloud.Service;

import tn.esprit.healthcloud.Entities.Donateur;
import tn.esprit.healthcloud.Entities.TypeSanguin;

import java.util.List;

public interface IserviceDonateur {

    Donateur ajouterDonateur(Donateur donateur);
    Donateur modifierDonateur(Long idDonateur, Donateur donateur);
    Donateur AfficherDonateurById(Long idDonateur);
    List<Donateur>afficherDonateurs();
    void supprimerDonateur(Long id);
    List<Donateur> getDonateursByTypeSanguin(TypeSanguin typeSanguin);


}
