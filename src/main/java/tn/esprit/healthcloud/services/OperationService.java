package esprit.etudiant.tn.healthcloud.services;


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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.io.Serializable;
import java.net.URLEncoder;
import javax.imageio.ImageIO;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class OperationService implements OperationInterface, Serializable {
    private final EmailService emailService;
    private OperationRepository operationRepository;
    private final LogistiqueService logistiqueService;

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




    @Transactional
    public ResponseEntity<Operation> addOperationWithLogistiques(Operation operation) throws Exception {
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
        Operation savedOperation = operationRepository.save(operation);

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
    @Override
    public Map<String, Double> getSuccessRatesByType() {
        List<String> typeOps = operationRepository.findDistinctTypeOp();
        Map<String, Double> successRatesByType = new HashMap<>();
        for (String typeOp : typeOps) {
            double successRate = calculateSuccessRateByType(typeOp);
            successRatesByType.put(typeOp, successRate);
        }
        return successRatesByType;
    }
    @Override
    public double calculateSuccessRateByType(String typeOp) {
        List<Operation> operations = operationRepository.findByTypeOp(typeOp);
        long successfulOperations = operations.stream()
                .filter(Operation::isSuccess)
                .count();
        return (double) successfulOperations / operations.size();
    }
}