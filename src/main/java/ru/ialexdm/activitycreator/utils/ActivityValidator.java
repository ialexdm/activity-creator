package ru.ialexdm.activitycreator.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.ialexdm.activitycreator.models.Activity;

@Component
public class ActivityValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Activity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Activity activity = (Activity) target;
        if (activity.getEnding() != null && activity.getBeginning() != null && activity.getBeginning().isAfter(activity.getEnding())){
            errors.rejectValue("ending","", "Activity must end after begin");
        }

    }
}
