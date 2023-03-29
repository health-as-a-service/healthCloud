package tn.esprit.etudiant.healthcloud.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Infirmier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInf;
    private String nomInf;
    private String prenomInf;
    private String username;
    private String password;
    private Boolean statut;
    private String mail;
    private Boolean AdministrationMedicament;
}
