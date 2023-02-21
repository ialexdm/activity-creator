package ru.ialexdm.activitycreator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ialexdm.activitycreator.services.ActivitiesService;

@Controller
@RequestMapping("/activities")
public class ActivitiesController {
    private final ActivitiesService activitiesService;
    public ActivitiesController(ActivitiesService activitiesService) {
        this.activitiesService = activitiesService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("activities", activitiesService.findAll());
        return "activities/index";
    }
}
