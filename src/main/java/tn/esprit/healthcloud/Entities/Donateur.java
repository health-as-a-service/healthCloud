package tn.esprit.healthcloud.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Donateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("idDonateur")
    private Long idDonateur;

    @JsonProperty("nomDonateur")
    private String nomDonateur;

    @JsonProperty("prenomDonateur")
    private String prenomDonateur;

    @JsonProperty("adresse")
    private String adresse;

    @JsonProperty("numeroTelephone")
    private int numeroTelephone;

    @Enumerated(EnumType.STRING)
    @JsonProperty("typeSanguin")
    private TypeSanguin typeSanguin;

    @JsonProperty("quantitéTotale")
    private int quantitéTotale;

    @Temporal(TemporalType.DATE)
    @JsonProperty("dernierDon")
    private Date dernierDon;
    @OneToMany(mappedBy = "donateur", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BanqueSang> banquesSang;


}