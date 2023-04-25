package tn.esprit.healthcloud.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import tn.esprit.healthcloud.entities.Patient;
import tn.esprit.healthcloud.repositories.PatientRepository;
import tn.esprit.healthcloud.services.PatientService;


@Configuration
@EnableScheduling
public class ArchiveScheduling {
	
	
	@Autowired
	private PatientRepository patientRepo; 

	

	   // @Scheduled(cron = "0 0 0 * * ?") // checking every midnight
		@Scheduled(cron = "0 * * * * *")	
	    public void archive() {
	    	
	    	System.out.println("///////////////////////////////////////////////");
	//get patients to archive
	List<Patient> patientsToArchive = patientRepo.findAll()
			.stream()
		    .filter(patient -> {
		        LocalDate archivingDate = patient.getDateCreation().toLocalDate();
		        archivingDate.plusDays(patient.getSejour());
		        return archivingDate.equals(LocalDate.now());
		    })
		    .collect(Collectors.toList());
	
	//archiving
	patientsToArchive.forEach(patient->{
		patient.setIsArchive(true);
		patient.setDateArchivage(LocalDateTime.now());
	});
	System.out.println("////////////archiving//////////////////");
	patientRepo.saveAll(patientsToArchive);
	
	
	    }



	// @Scheduled(cron = "0 0 0 * * ?") // checking every midnight
	@Scheduled(cron = "0 * * * * *")
	    public void deleteFromArchive() {
	    	
	    	
	//get patients to delete from archive
	List<Patient> patientsToDeleteFromArchive = patientRepo.findAll()
			.stream()
		    .filter(patient -> {
		        LocalDate deleteFromArchiveDate = patient.getDateArchivage().toLocalDate();
		        deleteFromArchiveDate.plusYears(5);
		        return deleteFromArchiveDate.equals(LocalDate.now());
		    })
		    .collect(Collectors.toList());
	
	//deleting from archive
	patientRepo.deleteAll(patientsToDeleteFromArchive);
	
	
	    }
	    
	    
	    
	    
	    

	    }