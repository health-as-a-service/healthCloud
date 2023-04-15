package tn.esprit.healthcloud.Service;

import tn.esprit.healthcloud.Entities.BanqueSang;
import tn.esprit.healthcloud.Entities.TypeSanguin;
import tn.esprit.healthcloud.Repositories.BanqueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BanqueDeSangService implements IserviceBanque {

    BanqueRepository banqueRepository;

    public BanqueDeSangService(BanqueRepository banqueRepository) {
        this.banqueRepository = banqueRepository;
    }

    @Override
    public BanqueSang ajouterBanque(BanqueSang banqueSang) {
        return banqueRepository.save(banqueSang);
    }


    public BanqueSang modifierBanque(int idBanque, BanqueSang banqueSang) {
        banqueSang.setIdBanque(idBanque);
        return banqueRepository.save(banqueSang);
    }

    @Override
    public BanqueSang afficherBanqueById(int idBanque) {
        return banqueRepository.findById(idBanque).orElse(null);
    }

    @Override
    public List<BanqueSang> afficherBanque() {
        return banqueRepository.findAll();
    }



    @Override
    public void supprimerBanque(int idBanque) {
        banqueRepository.deleteById(idBanque);
    }

    @Override
    public List<Object[]> getDonateursAndQuantitesByTypeSanguin(TypeSanguin typeSanguin) {
        return null;
    }


    @Override
    public void augmenterQuantiteSang(TypeSanguin typeSanguin, int quantite) {
        BanqueSang banqueSang = banqueRepository.findByTypeSanguinDisponibleAndDonateurIsNull(typeSanguin);
        if (banqueSang != null) {
            banqueSang.setQuantiteDisponible(banqueSang.getQuantiteDisponible() + quantite);
            banqueRepository.save(banqueSang);
        } else {
            BanqueSang newBanqueSang = new BanqueSang();
            newBanqueSang.setDonateur(null);
            newBanqueSang.setTypeSanguinDisponible(typeSanguin);
            newBanqueSang.setQuantiteDisponible(quantite);
            banqueRepository.save(newBanqueSang);
        }
    }
}
