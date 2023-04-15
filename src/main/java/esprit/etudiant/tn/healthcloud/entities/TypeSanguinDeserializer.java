package esprit.etudiant.tn.healthcloud.entities;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import esprit.etudiant.tn.healthcloud.entities.TypeSanguin;

public class TypeSanguinDeserializer extends JsonDeserializer<TypeSanguin> {

    @Override
    public TypeSanguin deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        String typeSanguin = p.getText();
        return TypeSanguin.valueOf(typeSanguin.toUpperCase());
    }

}
