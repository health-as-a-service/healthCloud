package tn.esprit.healthcloud.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consultation")
    private Long id_consultation;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private Date date;

    @Column(name = "consultation_time")
    private LocalTime time;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_rdv")
    private StatusRDV statusRDV;

    @Column(name = "motif_consultation")
    private String motifConsultation;

    @ElementCollection
    private List<String> antecedentsMedicaux= new ArrayList<String>();

    @Column(name = "suivi")
    private String suivi;

   /* @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Patient patient;

    @ManyToOne
    private Medecin medecin; */

    @OneToOne
    @JoinColumn(name="facture_id", nullable=false)
    private Facture facture;

    @ManyToOne
    @JoinColumn(name="assurance_id", nullable=false)
    private Assurance assurance;

    /*@ManyToMany(mappedBy = "consultation")
    @JoinColumn(name = "laboratoire_id")
    private set<Laboratoire> laboratoire;

    @OneToOne
    @JoinColumn(name = "prescription_id")

    private Prescription prescription;*/
    private String comment;
    public String getComment() {
        return comment;
    }
    @JsonManagedReference
    @OneToMany(mappedBy = "consultation",cascade = CascadeType.ALL)
    private List<Sample> samples;
}
