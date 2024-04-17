package com.example.sakilademo.dto.output;

import com.example.sakilademo.entities.Film;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Year;

@Getter
@AllArgsConstructor
public class FilmReferenceOutput {
    private Short id;
    private String desc;
    private Year releaseYear;
    private String movieTitle;
    public static  FilmReferenceOutput from(Film film){
        return new FilmReferenceOutput(film.getId(), film.getDesc(),film.getReleaseYear(), film.getMovieTitle());
    }
}
