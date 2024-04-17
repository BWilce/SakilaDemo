package com.example.sakilademo.dto.output;

import com.example.sakilademo.entities.Actor;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ActorOutput {
    private Short id;
    private String firstName;
    private String lastName;
    private List<FilmReferenceOutput> films;
//private List<FilmOutput> films;
    public static ActorOutput from(Actor actor){
        return new ActorOutput(actor.getId(),
                actor.getFirstName(),
                actor.getLastName(),
                actor.getFilms().
                        stream().
                        map(FilmReferenceOutput::from).
                        collect(Collectors.toList())
        );
    }
    public String getFullName(){
        return firstName + " " + lastName;
    }
}
