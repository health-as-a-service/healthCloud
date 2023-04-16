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
public class Pharmacie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int Id;
    private String nom;
    private String location;
    private String email;
    private String telephone;
    private String HeuresOuverts;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "Pharmacie")
    private List<Pharmacien> Pharmaciens;

}
