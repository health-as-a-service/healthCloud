package esprit.etudiant.tn.healthcloud.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import esprit.etudiant.tn.healthcloud.entities.TypeSanguinDeserializer;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonDeserialize(using = TypeSanguinDeserializer.class)
public enum TypeSanguin {
    A_POSITIF,
    A_NEGATIF,
    B_POSITIF,
    B_NEGATIF,
    AB_POSITIF,
    AB_NEGATIF,
    O_POSITIF,
    O_NEGATIF

}
