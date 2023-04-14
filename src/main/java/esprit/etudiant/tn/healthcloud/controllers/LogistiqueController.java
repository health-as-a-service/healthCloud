package esprit.etudiant.tn.healthcloud.controllers;

import esprit.etudiant.tn.healthcloud.entities.Logistique;
import esprit.etudiant.tn.healthcloud.entities.Operation;
import esprit.etudiant.tn.healthcloud.services.LogistiqueService;
import esprit.etudiant.tn.healthcloud.services.OperationInterface;
import esprit.etudiant.tn.healthcloud.services.OperationService;
import esprit.etudiant.tn.healthcloud.services.ILogistiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/logistiques")
public class LogistiqueController {

    @Autowired
    private ILogistiqueService logistiqueService;

    @Autowired
    private OperationService operationService;

    @PostMapping("")
    public ResponseEntity<Logistique> addLogistique(@RequestBody Logistique logistique) {
        Logistique newLogistique = logistiqueService.addLogistique(logistique);
        return new ResponseEntity<>(newLogistique, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Logistique> getLogistiqueById(@PathVariable int id) {
        Logistique logistique = logistiqueService.getLogistiqueById(id);
        if (logistique == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(logistique, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogistique(@PathVariable int id) {
        logistiqueService.deleteLogistique(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("")
    public ResponseEntity<List<Logistique>> getAllLogistiques() {
        List<Logistique> logistiques = logistiqueService.getAllLogistiques();
        return new ResponseEntity<>(logistiques, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Logistique> updateLogistique(@RequestBody Logistique logistique) {
        Logistique updatedLogistique = logistiqueService.updateLogistique(logistique);
        return new ResponseEntity<>(updatedLogistique, HttpStatus.OK);
    }

    @GetMapping("/type/{typeLogi}")
    public ResponseEntity<List<Logistique>> searchLogistiqueByType(@PathVariable String typeLogi) {
        List<Logistique> logistiques = logistiqueService.searchLogistiqueByType(typeLogi);
        return new ResponseEntity<>(logistiques, HttpStatus.OK);
    }

    @GetMapping("/operation/{idOp}")
    public ResponseEntity<List<Logistique>> getLogistiquesByOperation(@PathVariable int idOp) {
        Operation operation = operationService.getOperationById(idOp);
        if (operation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Logistique> logistiques = logistiqueService.getLogistiquesByOperation(operation);
        return new ResponseEntity<>(logistiques, HttpStatus.OK);
    }
}
