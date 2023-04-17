package tn.esprit.healthcloud.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.healthcloud.config.CustomUserDetails;
import tn.esprit.healthcloud.entities.DayOff;
import tn.esprit.healthcloud.exceptions.ErrorResponse;
import tn.esprit.healthcloud.services.IDayOff;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/day-offs")
@AllArgsConstructor

public class DayOffController {

    private IDayOff dayOffService;

    @GetMapping("")
    public ResponseEntity<List<DayOff>> getAllDayOffs() {
        List<DayOff> dayOffs = dayOffService.getAllDayOffs();
        return new ResponseEntity<>(dayOffs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DayOff> getDayOffById(@PathVariable int id) {
        DayOff dayOff = dayOffService.getDayOffById(id);
        return new ResponseEntity<>(dayOff, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateDayOffStatus(@PathVariable int id, @RequestParam String newStatus) {
        dayOffService.updateDayOffStatus(id, newStatus);
    }

    @GetMapping("/pending")
    public List<DayOff> getPendingDayOffRequests() {
        return dayOffService.getPendingDayOffRequests();
    }

    @PostMapping("")
    public ResponseEntity<DayOff> createDayOff(@RequestBody DayOff dayOff) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails currentUser = (CustomUserDetails) authentication.getPrincipal();
        DayOff createdDayOff = dayOffService.createDayOff(dayOff, currentUser);
        return new ResponseEntity(createdDayOff.getId(), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDayOff(@PathVariable int id) {
        try{
        dayOffService.deleteDayOff(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);}
        catch(Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);        }
    }

    @GetMapping("/between-dates")
    public ResponseEntity<List<DayOff>> getDayOffsBetweenDates(
            @RequestParam("start-date") String startDate,
            @RequestParam("end-date") String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<DayOff> dayOffs = dayOffService.getDayOffsBetweenDates(start, end);
        return new ResponseEntity<>(dayOffs, HttpStatus.OK);
    }
}
