package tn.esprit.healthcloud.services;

import lombok.AllArgsConstructor;
import tn.esprit.healthcloud.entities.Logistique;
import tn.esprit.healthcloud.entities.Operation;
import tn.esprit.healthcloud.exceptions.ResourceNotFoundException;
import tn.esprit.healthcloud.repositories.LogistiqueRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class LogistiqueService implements ILogistiqueService, Serializable {

    private final LogistiqueRepository logistiqueRepository;

    @Override
    public Logistique addLogistique(Logistique logistique) {
        return logistiqueRepository.save(logistique);
    }

    @Override
    public void deleteLogistique(int idLogi) {
        logistiqueRepository.deleteById(idLogi);
    }

    @Override
    public List<Logistique> getAllLogistiques() {
        return logistiqueRepository.findAll();
    }

    @Override
    public List<Logistique> searchLogistiqueByType(String typeLogi) {
        return logistiqueRepository.findByTypeLogiContainingIgnoreCase(typeLogi);
    }

    @Override
    public Logistique updateLogistique(Logistique logistique) {
        return logistiqueRepository.save(logistique);
    }

    @Override
    public List<Logistique> getLogistiquesByOperation(Operation operation) {
        return logistiqueRepository.findByOperations(operation);
    }

    @Override
    public Logistique getLogistiqueById(int idLogi) {
        Optional<Logistique> optionalLogistique = logistiqueRepository.findById(idLogi);
        return optionalLogistique.orElseThrow(() -> new ResourceNotFoundException("Logistique", "id", idLogi));
    }

}
