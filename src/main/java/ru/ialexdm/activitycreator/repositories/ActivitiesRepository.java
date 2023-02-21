package ru.ialexdm.activitycreator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ialexdm.activitycreator.models.Activity;

@Repository
public interface ActivitiesRepository extends JpaRepository<Activity, Integer> {
}
