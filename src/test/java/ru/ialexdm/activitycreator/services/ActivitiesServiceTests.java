package ru.ialexdm.activitycreator.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.ialexdm.activitycreator.models.Activity;
import ru.ialexdm.activitycreator.repositories.ActivitiesRepository;
import ru.ialexdm.activitycreator.repositories.ParticipantsRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;

class ActivitiesServiceTests {

    @Mock
    private ActivitiesRepository activitiesRepository;
    @Mock
    private ParticipantsRepository participantsRepository;
    @InjectMocks
    private ActivitiesService activitiesService;
    private int id = 1;
    private Activity activity;



    @BeforeEach
    void setUp() {
        activitiesRepository = mock(ActivitiesRepository.class);
        activitiesService = new ActivitiesService(activitiesRepository, participantsRepository);
    }

    @Test
    void findAll() {
        activitiesService.findAll();
        verify(activitiesRepository, times(1)).findAll();
    }

    @Test
    void findOne_ShouldGetActivity_ExistingId() {
        activity = mock(Activity.class);
        when(activity.getId()).thenReturn(id);
        Optional<Activity> optional = mock(Optional.class);
        when(optional.orElse(null)).thenReturn(activity);
        when(activitiesRepository.findById(id)).thenReturn(optional);

        int foundID = activitiesService.findOne(id).getId();

        Assertions.assertEquals(id, foundID);

    }

    @Test
    void findOne_ShouldGetNull_NotExistingId() {

        Optional<Activity> optional = mock(Optional.class);
        when(optional.orElse(null)).thenReturn(null);
        when(activitiesRepository.findById(id)).thenReturn(optional);

        Assertions.assertNull(activitiesService.findOne(id));
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}