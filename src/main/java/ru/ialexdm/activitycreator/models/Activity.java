package ru.ialexdm.activitycreator.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotNull
    @Size(min = 2, max = 128)
    String title;

    @NotNull
    @Size(min = 2, max = 128)
    //TODO location should be geoPosition
    String site;

    @NotNull
            //TODO begin time should be after now
    LocalDateTime beginning;

    @NotNull
            //TODO finish time should be after begin time
    LocalDateTime ending;
}
