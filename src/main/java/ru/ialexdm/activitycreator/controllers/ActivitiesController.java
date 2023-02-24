package ru.ialexdm.activitycreator.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ialexdm.activitycreator.models.Activity;
import ru.ialexdm.activitycreator.models.Participant;
import ru.ialexdm.activitycreator.services.ActivitiesService;
import ru.ialexdm.activitycreator.utils.ActivityValidator;

@Controller
@RequestMapping("/activities")
public class ActivitiesController {
    private final ActivitiesService activitiesService;
    private final ActivityValidator activityValidator;
    public ActivitiesController(ActivitiesService activitiesService, ActivityValidator activityValidator) {
        this.activitiesService = activitiesService;
        this.activityValidator = activityValidator;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("activities", activitiesService.findAll());
        return "activities/index";
    }
    @GetMapping("/{id}")
    public String details(@PathVariable(name = "id") int id, @ModelAttribute("newParticipant")Participant participant, Model model){
        Activity activity = activitiesService.findOne(id);
        model.addAttribute("activity", activity);
        model.addAttribute("participants", activity.getParticipants());

        return "activities/details";
    }
    @GetMapping("/new")
    public String newActivity(@ModelAttribute("activity")Activity activity)
    {
        return "activities/new";
    }
    @PostMapping()
    public String create(@ModelAttribute("activity") @Valid Activity activity, BindingResult bindingResult)
    {
        activityValidator.validate(activity, bindingResult);

        if (bindingResult.hasErrors())
        {
            return "activities/new";
        }
        activitiesService.save(activity);
        return "redirect:/activities";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("activity", activitiesService.findOne(id));
        return "activities/edit";
    }
    @PatchMapping("{id}")
    public String edit(@PathVariable("id")int id, @ModelAttribute("activity") @Valid Activity activity, BindingResult bindingResult)
    {
        activityValidator.validate(activity, bindingResult);
        if (bindingResult.hasErrors())
        {
            return "activities/edit";
        }
        activitiesService.update(id, activity);
        return "redirect:/activities/{id}";
    }
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id){
        activitiesService.delete(id);
        return "redirect:/activities";
    }

    @PatchMapping("{id}/add-participant")
    public String addParticipant(@ModelAttribute("activity") Activity activity,@ModelAttribute("newParticipant")@Valid Participant participant, BindingResult bindingResult){
        if (bindingResult.hasErrors())
        {
            return "activities/details";
        }
        activitiesService.addParticipant(activity, participant);

        return "redirect:/activities/{id}";
    }

    @DeleteMapping("{id}/participant/{participant-id}")
    public String removeParticipant(
                                    @PathVariable("participant-id") int participantId){
        activitiesService.removeParticipant(participantId);
        return "redirect:/activities/{id}";
    }

}
