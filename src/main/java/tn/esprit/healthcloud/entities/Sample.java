package tn.esprit.healthcloud.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Sample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int Id;
    private String description;
    @Enumerated(EnumType.STRING)
    private testType testType;
    private String status;
    private String resultat;
    private Date testDate;
    @ManyToOne
    private Laboratoire labo;
}
