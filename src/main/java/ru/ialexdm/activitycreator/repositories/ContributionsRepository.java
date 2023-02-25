package ru.ialexdm.activitycreator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ialexdm.activitycreator.models.Contribution;
@Repository
public interface ContributionsRepository extends JpaRepository<Contribution, Integer> {
}
