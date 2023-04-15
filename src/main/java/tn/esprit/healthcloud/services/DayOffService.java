package tn.esprit.healthcloud.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.healthcloud.config.CustomUserDetails;
import tn.esprit.healthcloud.entities.DayOffStatus;
import tn.esprit.healthcloud.entities.DayOff;
import tn.esprit.healthcloud.entities.User;
import tn.esprit.healthcloud.exceptions.DayOffNotFoundException;
import tn.esprit.healthcloud.exceptions.DeleteForbiddenException;
import tn.esprit.healthcloud.repositories.DayOffRepository;
import tn.esprit.healthcloud.repositories.UserRepository;

import java.time.LocalDate;
import java.util.logging.Logger;

import java.util.List;


@Service
@AllArgsConstructor
public class DayOffService implements IDayOff {
    private static final Logger logger = Logger.getLogger(DayOffService.class.getName());

    DayOffRepository dayOffRepository;
    private final UserRepository userRepository;

    @Override
    public DayOff createDayOff(DayOff dayOff, CustomUserDetails c_user) {
        dayOff.setStatus(DayOffStatus.pending);
        User user = userRepository.findById(c_user.getId()).get();
        dayOff.setUser(user);
        return dayOffRepository.save(dayOff);
    }

    @Override
    public DayOff getDayOffById(int id) {
        DayOff dayOff = dayOffRepository.findById(id);
        if (dayOff == null) {
            throw new DayOffNotFoundException("Day off request with id " + id + " not found");
        }
        DayOffStatus status = dayOff.getStatus();
        if (status.equals(DayOffStatus.rejected)) {
            throw new RuntimeException("Cannot access day off request with status " + status);
        }
        return dayOff;
    }

    @Override
    public List<DayOff> getAllDayOffs() {
        return dayOffRepository.findAll();
    }

    public List<DayOff> getPendingDayOffRequests() {
        return dayOffRepository.findByStatus(DayOffStatus.pending);
    }

    public void updateDayOffStatus(int id, String newStatus) {
        DayOff dayOff = getDayOffById(id);
        dayOff.setStatus(DayOffStatus.valueOf(newStatus.toLowerCase()));
        dayOffRepository.save(dayOff);
    }

    @Override
    public void deleteDayOff(int id) {
        DayOff dayOff = getDayOffById(id);
        DayOffStatus status = dayOff.getStatus();
        if (status.equals(DayOffStatus.approved) || status.equals(DayOffStatus.pending)) {
            throw new DeleteForbiddenException("Cannot delete day off request with status " + status);
        }
        dayOffRepository.deleteById(id);
    }

    @Override
    public List<DayOff> getDayOffsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return dayOffRepository.findDayOffsBetweenDates(startDate, endDate);
    }
}
