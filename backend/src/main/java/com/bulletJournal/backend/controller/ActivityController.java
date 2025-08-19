package com.bulletJournal.backend.controller;

import com.bulletJournal.backend.model.Activity;
import com.bulletJournal.backend.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/activities")
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend to access
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public List<Activity> getAllActivities() {
        return activityService.getAllActivities();
    }

    @PostMapping
    public Activity createActivity(@RequestBody Activity activity) {
        return activityService.saveActivity(activity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(
            @PathVariable Long id,
            @RequestBody Activity updatedActivity) {
        Activity activity = activityService.updateActivity(id, updatedActivity);
        return ResponseEntity.ok(activity);
    }

    @GetMapping("/date/{date}")
    public List<Activity> getActivitiesByDate(@PathVariable LocalDate date) {
        return activityService.getActivitiesByDate(date);
    }

    @GetMapping("/category/{category}")
    public List<Activity> getActivitiesByCategory(@PathVariable String category) {
        return activityService.getActivitiesByCategory(category);
    }

    @GetMapping("/stats/daily/{date}")
    public Integer getDailyTotalMinutes(@PathVariable LocalDate date) {
        return activityService.getTotalMinutesByDate(date);
    }
}
