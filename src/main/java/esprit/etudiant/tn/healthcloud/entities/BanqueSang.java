package esprit.etudiant.tn.healthcloud.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BanqueSang implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBanqueSang;

    @Enumerated(EnumType.STRING)
    private TypeSanguin typeSanguin;

    @OneToMany(mappedBy ="banqueSang")
    @JsonProperty("donateurs")
    @JsonManagedReference
    private Set<Donateur> donateurs;

    @JsonProperty("quantiteTotale")
    private int quantiteTotale;

    // constructors, getters and setters, toString method
}
