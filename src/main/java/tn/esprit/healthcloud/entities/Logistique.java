// Logistique.java

package tn.esprit.healthcloud.entities;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idLogi")
public class Logistique implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLogi;

    private String typeLogi;

    private String nomLogi;

    private int nombreLogi;

    @ManyToMany(mappedBy = "logistiques")
    @JsonProperty("operations")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idOp")
    @JsonIdentityReference(alwaysAsId = true)
    private Set<Operation> operations = new HashSet<>();

    public void addOperationsToLogistique(Logistique logistique, Set<Operation> operations) {
        logistique.getOperations().addAll(operations);
    }

    public void addOperation(Operation operation) {
        this.operations.add(operation);
        operation.getLogistiques().add(this);
    }

    public void removeOperation(Operation operation) {
        this.operations.remove(operation);
        operation.getLogistiques().remove(this);
    }

    // getters and setters
}
