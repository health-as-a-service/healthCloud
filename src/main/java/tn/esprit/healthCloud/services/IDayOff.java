package tn.esprit.healthCloud.services;

import tn.esprit.healthCloud.config.CustomUserDetails;
import tn.esprit.healthCloud.entities.DayOff;

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
