package ru.ialexdm.activitycreator.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ialexdm.activitycreator.models.Activity;
import ru.ialexdm.activitycreator.repositories.ActivitiesRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ActivitiesService {

    private final ActivitiesRepository activitiesRepository;

    public ActivitiesService(ActivitiesRepository activitiesRepository) {
        this.activitiesRepository = activitiesRepository;
    }

    public List<Activity> findAll(){
        return activitiesRepository.findAll();
    }
}
