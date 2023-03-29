package tn.esprit.healthcloud.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class DayOff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    LocalDate startDate;
    LocalDate endDate;
    String reason;
    @Enumerated(EnumType.STRING)
    DayOffStatus status;

}
