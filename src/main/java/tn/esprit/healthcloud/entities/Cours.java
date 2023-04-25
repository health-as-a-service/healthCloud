package tn.esprit.healthcloud.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String course_name;
    Date start;
    Date end;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private User doctor;

    @ManyToMany
    @JoinTable(name = "cours_stagiaires",
            joinColumns = @JoinColumn(name = "cours_id"),
            inverseJoinColumns = @JoinColumn(name = "stagiaires_id"))

    List<User> stagiaires;

}

