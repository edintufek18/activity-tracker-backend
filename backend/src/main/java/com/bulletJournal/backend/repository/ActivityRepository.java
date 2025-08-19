package com.bulletJournal.backend.repository;

import com.bulletJournal.backend.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByDate(LocalDate date);
    List<Activity> findByCategory(String category);
    List<Activity> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
