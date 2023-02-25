package ru.ialexdm.activitycreator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ialexdm.activitycreator.models.Contribution;
import ru.ialexdm.activitycreator.models.Participant;
import ru.ialexdm.activitycreator.services.ContributionService;

@Controller
@RequestMapping("/contributions")
public class ContributionController {

    private final ContributionService contributionService;

    public ContributionController(ContributionService contributionService) {
        this.contributionService = contributionService;
    }


    @PostMapping
    public String create(
            @ModelAttribute("contribution") Contribution contribution,
            @ModelAttribute("participant") Participant participant)
    {
        contributionService.save(contribution, participant);
        return "redirect:/activities/" + participant.getActivity().getId();
    }
}
