package tn.esprit.healthcloud.Service;

import tn.esprit.healthcloud.Entities.BanqueSang;

import tn.esprit.healthcloud.Entities.TypeSanguin;

import java.util.List;

public interface IserviceBanque {
    BanqueSang ajouterBanque(BanqueSang banquesang);
    BanqueSang modifierBanque(int idBanque, BanqueSang banquesang);
    BanqueSang afficherBanqueById(int idBanque);
    List<BanqueSang> afficherBanque();
    void supprimerBanque(int idBanque);
    List<Object[]> getDonateursAndQuantitesByTypeSanguin(TypeSanguin typeSanguin);
    void augmenterQuantiteSang(TypeSanguin typeSanguin, int quantite);
}
