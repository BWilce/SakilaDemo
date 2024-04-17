package com.example.sakilademo.dto.output;

import com.example.sakilademo.entities.Actor;
import com.example.sakilademo.entities.Film;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Year;

@Getter
@AllArgsConstructor
public class ActorReferenceOutput {
    private Short id;
    private String fullName;

    public static  ActorReferenceOutput from(Actor actor){
        return new ActorReferenceOutput(actor.getId(),
                actor.getFirstName() + " " + actor.getLastName()
        );
    }
}
