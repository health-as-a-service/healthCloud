package esprit.etudiant.tn.healthcloud.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chirurgien")
public class Chirurgien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idChi;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;



    // getters and setters

    public int getIdChi() {
        return idChi;
    }

    public void setIdChi(int idChi) {
        this.idChi = idChi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }



    // constructors

    public Chirurgien() {
    }

    public Chirurgien(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

}
