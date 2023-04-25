package tn.esprit.healthcloud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.handler.MappedInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.healthcloud.entities.DossierMedical;
import tn.esprit.healthcloud.entities.Image;
import tn.esprit.healthcloud.entities.Patient;
import tn.esprit.healthcloud.repositories.ImageRepository;
import tn.esprit.healthcloud.services.IDossierMService;
import tn.esprit.healthcloud.services.IImageService;
import tn.esprit.healthcloud.services.IPatientService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PatientController {

	@Autowired
    IPatientService iPatientService;
	@Autowired
    IDossierMService iDossierMedical;
	@Autowired
    private IImageService imageService;
	@Autowired
    private ImageRepository imageRepo;   

    public PatientController(IPatientService iPatientService) {
        this.iPatientService = iPatientService; 
    }
    
    @PostMapping(value="/patient/{idDossier}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @CrossOrigin
    public  ResponseEntity<String> ajouterPatient( @PathVariable("idDossier") int idDossier,
    		@RequestPart("patient") String patient,
   		 @RequestParam(name = "patientfile") MultipartFile patientFile) {
       
       	//converting patient object from string to json
    	  Patient patientJson = new Patient();
  	  try {
  		ObjectMapper mapper = new ObjectMapper();
  		patientJson=mapper.readValue(patient, Patient.class);
  	} catch (Exception e) {
  		System.out.println(e.toString());
  	}

      	
     
      	  Image patientFiletosend = new Image();

            try {

                String fileName = imageService.save(patientFile);
                String imageUrl = imageService.getImageUrl(fileName);
                patientFiletosend.setPath(imageUrl);
                
            } catch (Exception e) {
            e.printStackTrace();
            }
      	  patientJson.setImage(patientFiletosend);

    	
    	
    	
      	patientJson.setDossierMedical(iDossierMedical.afficherDMedical(idDossier));
    	
    	if(iPatientService.ajouterPatient(patientJson)!=null) {
        	return ResponseEntity.status(HttpStatus.CREATED).body("Félicitation, patient crée avec succès");
        }else {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Désolé, mais la chambre est déja Occupée ! ");

        }
        
        
    }
    
    
    @PostMapping(value="/patient", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @CrossOrigin 
    public  ResponseEntity<String> ajouterPatientt(
    		@RequestPart("patient") String patient,
    		 @RequestParam(name = "patientfile") MultipartFile patientFile
    		 ) {
    	
    	
    	//converting patient object from string to json
  	  Patient patientJson = new Patient();
	  try {
		ObjectMapper mapper = new ObjectMapper();
		patientJson=mapper.readValue(patient, Patient.class);
	} catch (Exception e) {
		System.out.println(e.toString());
	}

    	
   
    	  Image patientFiletosend = new Image();

          try {

              String fileName = imageService.save(patientFile);
              String imageUrl = imageService.getImageUrl(fileName);
              patientFiletosend.setPath(imageUrl);
              
          } catch (Exception e) {
          e.printStackTrace();
          }
    	  patientJson.setImage(patientFiletosend);

    
    	//if room doesn't exists, patient will be stored to DB
    	if(iPatientService.ajouterPatient(patientJson)!=null) {
        	return ResponseEntity.status(HttpStatus.CREATED).body("Félicitation, patient crée avec succès");
        }else {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Désolé, mais la chambre est déja Occupée ! ");

        }
        
        
    }
    
    
    
//    @PutMapping("/patienttoarchive/{id}")
//    public Patient patientToArchive(@PathVariable("id") int id) {
//        return iPatientService.toArchive(id);
//    }
    
    
    @PutMapping("/patient")
    public Patient modifierPatient(@RequestBody Patient patient) {
        return iPatientService.modifierPatient(patient);
    }
    @GetMapping("/patient")
    @CrossOrigin
    public List<Patient> afficherPatients() {
        return iPatientService.afficherPatients();
    }
    
    
    @GetMapping("/patientArchived")
    @CrossOrigin
    public List<Patient> afficherPatientsArchived() {
        return iPatientService.afficherPatientsArchivés();
    }
    
    @GetMapping("/patient/{id}")
    public Patient afficherPatient(@PathVariable("id") int id) {
        return iPatientService.afficherPatient(id);
    }
    @DeleteMapping("/patient/{id}")
    @CrossOrigin
    public void supprimerPatient(@PathVariable("id")  int id) {
        iPatientService.supprimerPatient(id);
    }

    @PutMapping("/assign/{idP}/{idD}")
    public Patient assignerDossierToPatient(@PathVariable("idP")Integer idP, @PathVariable("idD")Integer idD) {
        return iPatientService.assignerDossierToPatient(idP, idD);
    }

    @GetMapping("/findByNomPAndPrenomP/{nom}/{prenom}")
    public List<Patient> findByNomPAndPrenomP(String nom, String prenom) {
        return iPatientService.findByNomPAndPrenomP(nom, prenom);
    }
}
