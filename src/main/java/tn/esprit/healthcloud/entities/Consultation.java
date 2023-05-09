package tn.esprit.healthcloud.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Consultation implements Serializable {
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


    @ManyToOne
    private Patient patient;

    @ManyToOne
    private User doctor;


    @OneToOne
    @JoinColumn(name="facture_id")
    private Facture facture;

    @ManyToOne
    @JoinColumn(name="assurance_id")
    private Assurance assurance;


    private String comment;
    public String getComment() {
        return comment;
    }
}
