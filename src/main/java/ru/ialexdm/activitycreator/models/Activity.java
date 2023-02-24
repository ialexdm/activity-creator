package ru.ialexdm.activitycreator.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotEmpty(message = "Field should not be empty")
    @Size(min = 2, max = 128,  message = "Field should be greater than 2 and less than 128 letters")
    String title;

    @NotEmpty(message = "Field should not be empty")
    @Size(min = 2, max = 128,  message = "Field should be greater than 2 and less than 128 letters")
    //TODO location should be geoPosition
    String site;

    @NotNull(message = "Field should not be empty")
    LocalDateTime beginning;

    @NotNull(message = "Field should not be empty")
    LocalDateTime ending;

    @OneToMany(mappedBy = "activity", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
            //TODO instead Hibernate: delete from participant where id=?
    //             Hibernate: delete from participant where activity_id=?
    List<Participant> participants;

}
