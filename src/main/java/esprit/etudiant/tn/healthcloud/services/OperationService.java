package esprit.etudiant.tn.healthcloud.services;

import esprit.etudiant.tn.healthcloud.entities.Logistique;
import esprit.etudiant.tn.healthcloud.entities.Operation;
import esprit.etudiant.tn.healthcloud.repositories.LogistiqueRepository;
import esprit.etudiant.tn.healthcloud.repositories.OperationRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;


import java.io.Serializable;
import java.util.*;
@AllArgsConstructor
@Service
public class OperationService implements OperationInterface, Serializable {

    private OperationRepository operationRepository;

    @Override
    public Operation addOperation(Operation operation) {
        return operationRepository.save(operation);
    }

    @Override
    public void deleteOperation(int idOp) {
        operationRepository.deleteById(idOp);
    }

    @Override
    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    @Override
    public List<Operation> searchOperationByType(String typeOp) {
        return operationRepository.findByTypeOpContainingIgnoreCase(typeOp);
    }

    @Override
    public Operation updateOperation(Operation operation) {
        return operationRepository.save(operation);
    }

    @Override
    public List<Logistique> getLogistiquesByOperation(int idOp) {
        return operationRepository.findLogistiquesByOperation(idOp);
    }
    @Override
    public Operation getOperationById(int idOp) {
        return operationRepository.findById(idOp).orElse(null);
    }
    @Override
    public List<Operation> findByTypeOp(String typeOp) {
        return operationRepository.findByTypeOp(typeOp);
    }
    public Map<String, Long> getTypeOpStatistics() {
        List<Object[]> results = operationRepository.countOperationsByTypeOp();
        Map<String, Long> statistics = new HashMap<>();
        for (Object[] result : results) {
            String typeOp = (String) result[0];
            Long count = (Long) result[1];
            statistics.put(typeOp, count);
        }
        return statistics;
    }




}