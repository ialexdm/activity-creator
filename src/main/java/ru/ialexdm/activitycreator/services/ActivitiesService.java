package ru.ialexdm.activitycreator.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ialexdm.activitycreator.models.Activity;
import ru.ialexdm.activitycreator.models.Participant;
import ru.ialexdm.activitycreator.repositories.ActivitiesRepository;
import ru.ialexdm.activitycreator.repositories.ParticipantsRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ActivitiesService {

    private final ActivitiesRepository activitiesRepository;
    private final ParticipantsRepository participantsRepository;

    public ActivitiesService(ActivitiesRepository activitiesRepository, ParticipantsRepository participantsRepository) {
        this.activitiesRepository = activitiesRepository;
        this.participantsRepository = participantsRepository;
    }

    public List<Activity> findAll(){
        return activitiesRepository.findAll();
    }

    public Activity findOne(int id) {
        Optional<Activity> activity = activitiesRepository.findById(id);
        return activity.orElse(null);
    }
    @Transactional
    public void save(Activity activity) {
        activitiesRepository.save(activity);
    }

    @Transactional
    public void update(int id, Activity activity) {
        activity.setId(id);
        activitiesRepository.save(activity);
    }

    @Transactional
    public void delete(int id) {
        activitiesRepository.deleteById(id);
    }

    @Transactional
    public void addParticipant(Activity activity, Participant participant) {
        participant.setActivity(activity);
        participantsRepository.save(participant);
    }

    @Transactional
    public void removeParticipant(int id) {
        participantsRepository.deleteById(id);
    }
}
