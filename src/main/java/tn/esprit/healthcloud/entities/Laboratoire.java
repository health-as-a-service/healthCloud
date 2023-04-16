package tn.esprit.healthcloud.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Laboratoire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int Id;
    private String Nom;
    private String Telephone;
    private String Email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="Laboratoire")
    private List<TechnicienBiologist> TechnicienBiologist;
    // private int IdConsultationDemande;
}
