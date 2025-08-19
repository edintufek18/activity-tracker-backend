package com.bulletJournal.backend.service;

import com.bulletJournal.backend.model.Activity;
import com.bulletJournal.backend.repository.ActivityRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public Activity saveActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }

    public Activity updateActivity(Long id, Activity updatedActivity) {
        return activityRepository.findById(id)
                .map(activity -> {
                    activity.setName(updatedActivity.getName());
                    activity.setDescription(updatedActivity.getDescription());
                    activity.setCategory(updatedActivity.getCategory());
                    activity.setDate(updatedActivity.getDate());
                    activity.setDurationMinutes(updatedActivity.getDurationMinutes());

                    return activityRepository.save(activity);
                })
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + id));
    }

    public List<Activity> getActivitiesByDate(LocalDate date) {
        return activityRepository.findByDate(date);
    }

    public List<Activity> getActivitiesByCategory(String category) {
        return activityRepository.findByCategory(category);
    }

    // Additional methods for statistics
    public Integer getTotalMinutesByDate(LocalDate date) {
        return activityRepository.findByDate(date).stream()
                .mapToInt(Activity::getDurationMinutes)
                .sum();
    }
}
