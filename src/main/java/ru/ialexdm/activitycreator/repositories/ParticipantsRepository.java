package ru.ialexdm.activitycreator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ialexdm.activitycreator.models.Participant;

@Repository
public interface ParticipantsRepository extends JpaRepository<Participant, Integer> {
}
