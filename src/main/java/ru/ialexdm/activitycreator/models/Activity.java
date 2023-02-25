package ru.ialexdm.activitycreator.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Data
@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Field should not be empty")
    @Size(min = 2, max = 128,  message = "Field should be greater than 2 and less than 128 letters")
    private String title;

    @NotEmpty(message = "Field should not be empty")
    @Size(min = 2, max = 128,  message = "Field should be greater than 2 and less than 128 letters")
    //TODO location should be geoPosition
    private String site;

    @NotNull(message = "Field should not be empty")
    private LocalDateTime beginning;

    @NotNull(message = "Field should not be empty")
    private LocalDateTime ending;

    @OneToMany(mappedBy = "activity", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
            //TODO instead Hibernate: delete from participant where id=?
    //             Hibernate: delete from participant where activity_id=?
    private List<Participant> participants;

    @Min(value = 0, message = "Amount shouldn't be negative")
    private int amount;

    private int getContributions(){
        AtomicInteger contributions = new AtomicInteger();
        participants.forEach(participant -> contributions.addAndGet(participant.getContributionAmount()));
        return contributions.get();
    }
    public int getRemains(){
        return amount - getContributions();
    }

}
