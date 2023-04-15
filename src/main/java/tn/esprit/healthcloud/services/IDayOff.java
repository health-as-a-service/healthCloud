package tn.esprit.healthcloud.services;

import tn.esprit.healthcloud.entities.DayOff;

import java.time.LocalDate;
import java.util.List;

public interface IDayOff {

    DayOff createDayOff(DayOff dayOff);

    List<DayOff> getAllDayOffs();

    DayOff getDayOffById(int id);

    void deleteDayOff(int id);

    List<DayOff> getDayOffsBetweenDates(LocalDate startDate, LocalDate endDate);

    List<DayOff> getPendingDayOffRequests();

    void updateDayOffStatus(int id, String newStatus);
}
