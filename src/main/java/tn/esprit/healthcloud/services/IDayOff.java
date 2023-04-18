package tn.esprit.healthcloud.services;

import tn.esprit.healthcloud.config.CustomUserDetails;
import tn.esprit.healthcloud.entities.DayOff;

import java.time.LocalDate;
import java.util.List;

public interface IDayOff {

    List<DayOff> getAllDayOffs();

    DayOff createDayOff(DayOff dayOff, CustomUserDetails c_user);

    DayOff getDayOffById(int id);

    void deleteDayOff(int id);

    List<DayOff> getDayOffsBetweenDates(LocalDate startDate, LocalDate endDate);

    List<DayOff> getPendingDayOffRequests();

    void updateDayOffStatus(int id, String newStatus);
}
