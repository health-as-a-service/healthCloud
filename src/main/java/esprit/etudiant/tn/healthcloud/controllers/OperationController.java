package esprit.etudiant.tn.healthcloud.controllers;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sun.istack.ByteArrayDataSource;
import esprit.etudiant.tn.healthcloud.entities.Logistique;
import esprit.etudiant.tn.healthcloud.entities.Operation;
import esprit.etudiant.tn.healthcloud.repositories.OperationRepository;
import esprit.etudiant.tn.healthcloud.services.*;
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
public class OperationController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private OperationService operationService;
    @Autowired
    private LogistiqueService logistiqueService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private OperationRepository operationRepository;

    @PostMapping(value = "/oplogi", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Operation> addOperationWithLogistiques(@RequestBody Operation operation) throws Exception {
        Set<Logistique> logistiques = operation.getLogistiques().stream()
                .map(logistique -> logistiqueService.getLogistiqueById(logistique.getIdLogi()))
                .peek(savedLogistique -> {
                    if (savedLogistique.getNombreLogi() <= 0) {
                        throw new RuntimeException("Logistique with ID " + savedLogistique.getIdLogi() + " is out of stock!");
                    }
                    savedLogistique.setNombreLogi(savedLogistique.getNombreLogi() - 1);
                })
                .collect(Collectors.toSet());
        operation.setLogistiques(logistiques);
        Operation savedOperation = operationService.addOperation(operation);

        // Generate QR code
        String hospitalLocation = "Mami Hospital, Rue El Farabi, Ariana, Tunisia";
        String encodedLocation = URLEncoder.encode(hospitalLocation, String.valueOf(StandardCharsets.UTF_8));
        String qrCodeText = String.format("Operation of type: %s, Date: %s, Room: %s, Location: %s",
                savedOperation.getTypeOp(),
                savedOperation.getDateOp(),
                savedOperation.getIdChambre(),
                "https://www.google.com/maps/search/?api=1&query=" + encodedLocation);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, 350, 350);
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        // Convert QR code image to base64 string
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        String qrCodeImage = Base64.getEncoder().encodeToString(baos.toByteArray());

        String emailBody = "Operation of type : " + savedOperation.getTypeOp() + " date : "+savedOperation.getDateOp() + " the room will be : " +savedOperation.getIdChambre()
                + " has been added. Please find the attached QR code containing operation details.";

        // Add QR code image as an attachment to email
        ByteArrayDataSource qrCodeAttachment = new ByteArrayDataSource(baos.toByteArray(), "image/png");
        emailService.sendEmailqr("mohamediheb.berraies@esprit.tn", "New Operation Added", emailBody, qrCodeAttachment, "qr_code.png");

        logistiques.forEach(logistique -> {
            String message = "The logistique with id " + logistique.getIdLogi() + " is out of stock!";
            // send notification to user here
        });

        return new ResponseEntity<>(savedOperation, HttpStatus.CREATED);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Operation> getOperationById(@PathVariable int id) {
        Operation operation = operationService.getOperationById(id);
        if (operation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(operation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOperation(@PathVariable int id) {
        operationService.deleteOperation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("")
    public ResponseEntity<List<Operation>> getAllOperations() {
        List<Operation> operations = operationService.getAllOperations();
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Operation> updateOperation(@RequestBody Operation operation) {
        Operation updatedOperation = operationService.updateOperation(operation);
        return new ResponseEntity<>(updatedOperation, HttpStatus.OK);
    }
    @GetMapping("/operations/type/{type}")
    public ResponseEntity<List<Operation>> findByTypeOp(@PathVariable String type) {
        List<Operation> operations = operationService.findByTypeOp(type);
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }
    @GetMapping("/operations/anytype/{type}")
    public ResponseEntity<List<Operation>> searchOperationByType(@PathVariable String type) {
        List<Operation> operations = operationService.searchOperationByType(type);
        if (operations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }
    @GetMapping("/typeOp")
    public Map<String, Long> getTypeOpStatistics() {
        return operationService.getTypeOpStatistics();
    }

    @GetMapping("/success-rates")
    public Map<String, Double> getSuccessRatesByType() {
        List<String> typeOps = operationRepository.findDistinctTypeOp();
        Map<String, Double> successRatesByType = new HashMap<>();
        for (String typeOp : typeOps) {
            double successRate = calculateSuccessRateByType(typeOp);
            successRatesByType.put(typeOp, successRate);
        }
        return successRatesByType;
    }

    private double calculateSuccessRateByType(String typeOp) {
        List<Operation> operations = operationRepository.findByTypeOp(typeOp);
        long successfulOperations = operations.stream()
                .filter(Operation::isSuccess)
                .count();
        return (double) successfulOperations / operations.size();
    }










}

