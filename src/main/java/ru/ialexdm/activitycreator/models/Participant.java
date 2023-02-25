package ru.ialexdm.activitycreator.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Participant name must be from 2 to 128 letters")
    @Size(min = 2, max = 128)
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
    Activity activity;


    /**
     * Method returns required amount for activity
     * @return required amount is activity amount per participant
     */
    public int getRequiredAmount(){
        return activity.amount / activity.getParticipants().size();
    }
}
