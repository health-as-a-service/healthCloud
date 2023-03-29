package tn.esprit.healthcloud.entities;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Administration implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY )
    private int idAdmin;
    private String nomAdmin;
    private String prenomAdmin;
    private String username;
    private String password;
    private Boolean statut;
    private String mail;
    @Enumerated(EnumType.STRING)
    private Job job;

}
