package tn.esprit.healthcloud.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Patient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idP;
    String EmailP;
    String nomP;
    String prenomP;
    String sexe;
    LocalDate dateNaissanceP;
    String numTel;
    long chambre;
    long sejour;
    Boolean isArchive;
    LocalDateTime dateArchivage;
    LocalDate dateCreation;
 

    //patient-dossierM
    @OneToOne(cascade = CascadeType.ALL)
    private DossierMedical dossierMedical;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Image image;



}