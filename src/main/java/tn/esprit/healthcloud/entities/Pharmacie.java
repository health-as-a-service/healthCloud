package tn.esprit.healthcloud.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Pharmacie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int Id;
    private String nom;
    private String location;
    private String email;
    private String telephone;
    private String HeuresOuverts;
    @JsonManagedReference
    @OneToMany(mappedBy = "pharmacie",cascade = CascadeType.ALL)
    private List<Medicament> Medicaments;

}
