package com.example.sakilademo.controllers;

import com.example.sakilademo.dto.input.ActorInput;
import com.example.sakilademo.dto.input.ValidationGroup;
import com.example.sakilademo.dto.output.ActorOutput;
import com.example.sakilademo.entities.Actor;
import com.example.sakilademo.entities.Film;
import com.example.sakilademo.handler.CustomException;
import com.example.sakilademo.handler.SpecificIDError;
import com.example.sakilademo.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.data.domain.Pageable;
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
    @Autowired
    private PagedResourcesAssembler<Actor> pagedResourcesAssembler;
    @GetMapping
    public PagedModel<EntityModel<ActorOutput>> readAll(@RequestParam(defaultValue = "0")int page,
                                                        @RequestParam(defaultValue = "2")int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Actor> actorPage = actorRepository.findAll(pageable);
        // Don't need to use a variable.
        return pagedResourcesAssembler.toModel(
                actorPage, actor -> EntityModel.of(ActorOutput.from(actor)));
    }
    @GetMapping("/{id}")
    public ActorOutput readByID(@PathVariable Short id){
        return actorRepository.findById(id)
                .map(ActorOutput::from)
        .orElseThrow(()-> new SpecificIDError(id, HttpStatus.NOT_FOUND));
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
                .orElseThrow(()-> new SpecificIDError(id, HttpStatus.NOT_FOUND));
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
                .orElseThrow(() -> new SpecificIDError(id, HttpStatus.NOT_FOUND));
        actorRepository.delete(actor);
    }
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException ex){
        HttpStatus httpStatus = ex.getHttpStatus();
        return ResponseEntity.status(httpStatus)
                .body(ex.getMessage()+" ("+
                        httpStatus.value()+
                        " "+
                        httpStatus.getReasonPhrase()+")");}
}
