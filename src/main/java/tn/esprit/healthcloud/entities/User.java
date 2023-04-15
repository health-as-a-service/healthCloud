package tn.esprit.healthcloud.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;
    private String username;
    private String email;
    private String password;
    private String nom;
    private String prenom;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "idRole"))
    private Set<Role> roles = new HashSet<>();
    private Boolean statut;
    @Temporal(TemporalType.DATE)
    private Date dateDebutStage;
    @Temporal(TemporalType.DATE)
    private Date dateFinStage;
    private String specialite;
    @Enumerated(EnumType.STRING)
    private Job job;


    public User(String username, String email, String encode) {
    }
}
