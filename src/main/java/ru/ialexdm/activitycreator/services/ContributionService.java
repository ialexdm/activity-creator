package ru.ialexdm.activitycreator.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ialexdm.activitycreator.models.Contribution;
import ru.ialexdm.activitycreator.models.Participant;
import ru.ialexdm.activitycreator.repositories.ContributionsRepository;

@Service
@Transactional(readOnly = true)
public class ContributionService {
    private final ContributionsRepository contributionsRepository;

    public ContributionService(ContributionsRepository contributionsRepository) {
        this.contributionsRepository = contributionsRepository;
    }

    @Transactional
    public void save(Contribution contribution, Participant participant) {
        if (contribution.getAmount() != 0) {
            contribution.setParticipant(participant);
            contributionsRepository.save(contribution);
        }else {
            System.out.println("0 value contribution");
        }
    }
}
