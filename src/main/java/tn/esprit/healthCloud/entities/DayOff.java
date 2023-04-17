package tn.esprit.healthCloud.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DayOff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    LocalDate startDate;
    LocalDate endDate;
    String reason;
    @Enumerated(EnumType.STRING)
    DayOffStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public String toString() {
        return "DayOff{" +
                "id=" + id +
                ", start=" + startDate +
                ", end=" + endDate +
                ",  status=" + status +
                ", user_id=" + user.getIdUser() +
                ", reason=" + reason +

                '}';
    }
}
