package esprit.etudiant.tn.healthcloud.controllers;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sun.istack.ByteArrayDataSource;
import esprit.etudiant.tn.healthcloud.entities.Logistique;
import esprit.etudiant.tn.healthcloud.entities.Operation;
import esprit.etudiant.tn.healthcloud.repositories.OperationRepository;
import esprit.etudiant.tn.healthcloud.services.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import java.net.URLEncoder;
import javax.imageio.ImageIO;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/operations")
@AllArgsConstructor
public class OperationController {
    private final NotificationService notificationService;
    private final OperationInterface operationInterface;
    private final ILogistiqueService logistiqueService;
    private final EmailService emailService;
    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping(value = "/oplogi", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Operation> addOperationWithLogistiques(@RequestBody Operation operation) throws Exception {
        return operationInterface.addOperationWithLogistiques(operation);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/{id}")
    public ResponseEntity<Operation> getOperationById(@PathVariable int id) {
        Operation operation = operationInterface.getOperationById(id);
        if (operation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(operation, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperation(@PathVariable int id) {
        operationInterface.deleteOperation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("")
    public ResponseEntity<List<Operation>> getAllOperations() {
        List<Operation> operations = operationInterface.getAllOperations();
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @PutMapping("")
    public ResponseEntity<Operation> updateOperation(@RequestBody Operation operation) {
        Operation updatedOperation = operationInterface.updateOperation(operation);
        return new ResponseEntity<>(updatedOperation, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/operations/type/{type}")
    public ResponseEntity<List<Operation>> findByTypeOp(@PathVariable String type) {
        List<Operation> operations = operationInterface.findByTypeOp(type);
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/operations/anytype/{type}")
    public ResponseEntity<List<Operation>> searchOperationByType(@PathVariable String type) {
        List<Operation> operations = operationInterface.searchOperationByType(type);
        if (operations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/typeOp")
    public Map<String, Long> getTypeOpStatistics() {
        return operationInterface.getTypeOpStatistics();
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/success-rates")
    public Map<String, Double> getSuccessRatesByType() {
        return operationInterface.getSuccessRatesByType();
    }


}
