package com.example.sakilademo.dto.output;

import com.example.sakilademo.entities.Film;
import com.example.sakilademo.entities.FilmRating;
import com.example.sakilademo.entities.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class FilmOutput {
    private Short id;
    private String desc;
    private BigDecimal rentalRate;
    private String movieTitle;
    //Decouple in the future. Language output class.
    private Year releaseYear;
    private FilmRating rating;
    private Language languageID;
//    private String languageID;
    private List<CategoryReferenceOutput> categories;
    private List<ActorReferenceOutput> cast;


    public static FilmOutput from(Film film){
        return new FilmOutput(
                film.getId(),
                film.getDesc(),
                film.getRentalRate(),
                film.getMovieTitle(),
                film.getReleaseYear(),
                film.getRating(),
                film.getLanguage(),
                film.getCategories()
                        .stream()
                        .map(CategoryReferenceOutput::from)
                        .collect(Collectors.toList()),
                film.getCast()
                        .stream()
                        .map(ActorReferenceOutput::from)
                        .collect(Collectors.toList())
                );
    }
//    .getName()
}
