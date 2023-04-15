package esprit.etudiant.tn.healthcloud.services;

import esprit.etudiant.tn.healthcloud.entities.BanqueSang;
import java.util.List;
import java.util.Optional;

public interface IbanqueSangService {

    List<BanqueSang> getAllBanqueSangs();
    Optional<BanqueSang> getBanqueSangById(int id);
    BanqueSang addBanqueSang(BanqueSang banqueSang);
    BanqueSang updateBanqueSang(BanqueSang banqueSang);
    void deleteBanqueSangById(int id);
}
