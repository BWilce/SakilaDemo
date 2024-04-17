package com.example.sakilademo.controllers;

import com.example.sakilademo.dto.input.FilmInput;
import com.example.sakilademo.dto.input.ValidationGroup;
import com.example.sakilademo.dto.output.ActorOutput;
import com.example.sakilademo.dto.output.FilmOutput;
import com.example.sakilademo.entities.Actor;
import com.example.sakilademo.entities.Film;
import com.example.sakilademo.entities.FilmRating;
import com.example.sakilademo.entities.Language;
import com.example.sakilademo.repositories.LanguageRepository;
import com.example.sakilademo.repositories.FilmRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

//import com.example.sakilademo.wrapper.Pagination;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.sakilademo.dto.input.ValidationGroup.Create;
import static com.example.sakilademo.dto.input.ValidationGroup.Update;
@RestController
@RequestMapping("/film")
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private PagedResourcesAssembler<Film> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<FilmOutput>> readAll(@RequestParam(defaultValue = "0")int page,
                                                       @RequestParam(defaultValue = "2")int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Film> filmPage = filmRepository.findAll(pageable);
        return pagedResourcesAssembler.toModel(
                filmPage, film -> EntityModel.of(FilmOutput.from(film)));
    }
//    public List<FilmOutput> readAll(@RequestParam(defaultValue = "0")int page,
//                                    @RequestParam(defaultValue = "2")int size)
//    {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Film> filmPage = filmRepository.findAll(pageable);
//        return filmPage.getContent()
//                .stream()
//                .map(FilmOutput::from)
//                .collect(Collectors.toList());
////        return filmRepository.findAll()
////                .stream()
////                .map(FilmOutput::from)
////                .collect(Collectors.toList());
//    }

    @GetMapping("/{id}")
    public FilmOutput readByID(@PathVariable Short id) {
        return filmRepository.findById(id)
                .map(FilmOutput::from)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("No such film with id %d.", id)
                ));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public FilmOutput create(@Validated(Create.class)
                             @RequestBody FilmInput data) {
        FilmRating rating = FilmRating.ratingData(data.getRating());
        Language language = languageRepository.findById(data.getLanguageID())
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Language not found"));
        final var film = new Film();
        film.setMovieTitle(data.getMovieTitle());
        film.setReleaseYear(data.getReleaseYear());
        film.setLanguage(language);
        film.setRating(rating);
//        film.setRentalRate(data.getRentalRate() != null ? data.getRentalRate() : BigDecimal.valueOf(4.99));
        return FilmOutput.from(filmRepository
                .save(film));
    }
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FilmOutput update(@PathVariable Short id,
                             @Validated(Update.class)
                             @RequestBody FilmInput data){
        Language language = languageRepository.findById(data.getLanguageID())
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Language not found"));
        Film film = filmRepository.findById(id).orElseThrow(()-> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "No such film with id "+id));
        if (data.getMovieTitle() != null){
            film.setMovieTitle(data.getMovieTitle());
        }
        if (data.getReleaseYear() != null){
            film.setReleaseYear(data.getReleaseYear());
        }
        if (data.getLanguageID() != null){
            film.setLanguage(language);
        }
        Film updateFilm = filmRepository.save(film);
        return FilmOutput.from(updateFilm);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Short id){
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No such film with id " + id));
    filmRepository.delete(film);
}
//private String generatePageLink(int page, int size, int totalPages, String baseURL){
//        if (page < 0 || page >= totalPages) return null;
//
//        return UriComponentsBuilder.fromUriString(baseURL)
//                .queryParam("page",page)
//                .queryParam("size",size)
//                .build()
//                .toUriString();
//}
}