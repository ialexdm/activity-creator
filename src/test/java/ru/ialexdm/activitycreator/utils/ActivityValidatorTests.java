package ru.ialexdm.activitycreator.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;
import ru.ialexdm.activitycreator.models.Activity;
import java.time.LocalDateTime;
import java.time.Month;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ActivityValidatorTests {


    private Activity activity;
    private Errors errors;
    private ActivityValidator activityValidator;


    private LocalDateTime beginning = LocalDateTime.of(2023,Month.DECEMBER,1,10,10);


    @BeforeEach
    public void setUp(){
        activityValidator = new ActivityValidator();
        activity = mock(Activity.class);
        errors = mock(Errors.class);
        when(activity.getBeginning()).thenReturn(beginning);
    }


    @Test
    public void validate_ShouldAcceptActivity_WithValidEnding()
    {
        LocalDateTime validEnding = LocalDateTime.of(2023,Month.DECEMBER,1,10,11);
        when(activity.getEnding()).thenReturn(validEnding);

        activityValidator.validate(activity, errors);

        verify(errors , never()).rejectValue(eq("ending"), any(), any());
    }
    @Test
    public void validate_ShouldRejectActivity_WithInvalidEndingTime()
    {
        LocalDateTime invalidTimeEnding = LocalDateTime.of(2023,Month.NOVEMBER,1,9,11);
        when(activity.getEnding()).thenReturn(invalidTimeEnding);

        activityValidator.validate(activity, errors);

        verify(errors , times(1)).rejectValue(eq("ending"), any(), any());
    }
    @Test
    public void validate_ShouldRejectActivity_WithInvalidEndingDate()
    {
        LocalDateTime invalidDateEnding = LocalDateTime.of(2023,Month.NOVEMBER,1,10,11);
        when(activity.getEnding()).thenReturn(invalidDateEnding);

        activityValidator.validate(activity, errors);

        verify(errors , times(1)).rejectValue(eq("ending"), any(), any());
    }
}
