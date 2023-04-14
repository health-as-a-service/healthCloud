package esprit.etudiant.tn.healthcloud.services;

import esprit.etudiant.tn.healthcloud.entities.BanqueSang;
import esprit.etudiant.tn.healthcloud.entities.TypeSanguin;
import esprit.etudiant.tn.healthcloud.repositories.BanqueSangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BanqueSangService implements IbanqueSangService {

    @Autowired
    BanqueSangRepository banqueSangRepository;

    @Override
    public List<BanqueSang> getAllBanqueSangs() {
        return banqueSangRepository.findAll();
    }

    @Override
    public Optional<BanqueSang> getBanqueSangById(int id) {
        return banqueSangRepository.findById(id);
    }

    @Override
    public BanqueSang addBanqueSang(BanqueSang banqueSang) {
        return banqueSangRepository.save(banqueSang);
    }

    @Override
    public BanqueSang updateBanqueSang(BanqueSang banqueSang) {
        return banqueSangRepository.save(banqueSang);
    }

    @Override
    public void deleteBanqueSangById(int id) {
        banqueSangRepository.deleteById(id);
    }
    public Optional<BanqueSang> findByTypeSanguin(TypeSanguin typeSanguin) {
        return Optional.ofNullable(banqueSangRepository.findByTypeSanguin(typeSanguin));
    }

}
