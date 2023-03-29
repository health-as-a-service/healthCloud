package tn.esprit.healthcloud.entities;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Stagiare implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSta;
    private String nomSta;
    private String prenomSta;
    private String username;
    private String password;
    private Boolean statut;
    private String mail;
    @Temporal(TemporalType.DATE)
    private Date dateDebutStage;
    @Temporal(TemporalType.DATE)
    private Date dateFinStage;


}