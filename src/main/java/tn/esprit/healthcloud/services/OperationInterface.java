package esprit.etudiant.tn.healthcloud.services;



import esprit.etudiant.tn.healthcloud.entities.Logistique;
import esprit.etudiant.tn.healthcloud.entities.Operation;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface OperationInterface {
    Operation addOperation(Operation operation);

    void deleteOperation(int idOp);

    List<Operation> getAllOperations();

    List<Operation> searchOperationByType(String typeOp);

    Operation updateOperation(Operation operation);

    List<Logistique> getLogistiquesByOperation(int idOp);
    Operation getOperationById(int idOp);
    List<Operation> findByTypeOp(String typeOp);
    ResponseEntity<Operation> addOperationWithLogistiques(Operation operation) throws Exception;
    Map<String, Long> getTypeOpStatistics();

    public Map<String, Double> getSuccessRatesByType();
    double calculateSuccessRateByType(String typeOp);
}

