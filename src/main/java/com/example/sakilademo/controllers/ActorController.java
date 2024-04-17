package com.example.sakilademo.controllers;

import com.example.sakilademo.dto.input.ActorInput;
import com.example.sakilademo.dto.input.ValidationGroup;
import com.example.sakilademo.dto.output.ActorOutput;
import com.example.sakilademo.entities.Actor;
import com.example.sakilademo.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static com.example.sakilademo.dto.input.ValidationGroup.Create;
import static com.example.sakilademo.dto.input.ValidationGroup.Update;
@RestController
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;
    @GetMapping
    public List<ActorOutput> readAll(){
        // Don't need to use a variable.
        final var actors = actorRepository.findAll();
        return actors
                .stream()
                .map(ActorOutput::from)
                .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ActorOutput readByID(@PathVariable Short id){
        return actorRepository.findById(id)
                .map(ActorOutput::from)
        .orElseThrow(()-> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("No such actor with id %d.", id)
                ));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActorOutput create(@Validated(Create.class) @RequestBody ActorInput data){
        final var actor = new Actor();
        actor.setFirstName(data.getFirstName());
        actor.setLastName(data.getLastName());
        return ActorOutput.from(actorRepository
                .save(actor));
    }
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ActorOutput update(@PathVariable Short id,@Validated(Update.class) @RequestBody ActorInput data){
        Actor actor = actorRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No such actor with id "+id));
        if (data.getFirstName() != null){
            actor.setFirstName(data.getFirstName());
        }
        if (data.getLastName() != null){
            actor.setLastName(data.getLastName());
        }
        Actor updateActor = actorRepository.save(actor);
        return ActorOutput.from(updateActor);
//        final var actor = new Actor();
//        actor.setFirstName(data.getFirstName());
//        actor.setLastName(data.getLastName());
//        return ActorOutput.from(actorRepository
//                .save(actor));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Short id){
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No such actor with id " + id));
        actorRepository.delete(actor);
    }
}
