package ru.ialexdm.activitycreator.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Entity
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Participant name must be from 2 to 128 letters")
    @Size(min = 2, max = 128)
    private String name;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @OneToMany (mappedBy = "participant", cascade = CascadeType.REMOVE)
    List<Contribution> contributions;

    /**
     * Method returns required amount for activity
     * @return required amount is activity amount per participant
     */

    public int getRequiredAmount(){
        return (activity.getAmount() / activity.getParticipants().size()) - getContributionAmount();
    }
    public int getContributionAmount(){
        AtomicInteger amount = new AtomicInteger();
        contributions.forEach(contribution -> amount.addAndGet(contribution.getAmount()));
        return amount.get();
    }
}
