package tn.esprit.healthcloud.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class TechnicienBiologist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int Id;
    private String nom;
    private String email;
    private String specialite;
    private int telephone;
    @ManyToOne
    private Laboratoire Laboratoire;
}
