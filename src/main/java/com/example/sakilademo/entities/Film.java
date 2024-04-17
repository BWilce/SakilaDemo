package com.example.sakilademo.entities;

import com.example.sakilademo.converter.String2Enum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Generated;

import java.math.BigDecimal;
import java.time.Year;
import java.util.*;

@Entity
@Table(name = "film")
@Getter
@Setter
public class Film {
//    public Object setMovieTitle;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="film_id")
    private Short id;
    @Column(name = "description")
    private String desc;
    @Column(name = "rental_rate", precision = 4, scale = 2)
    @Generated
    private BigDecimal rentalRate;
    @Column(name = "title")
    private String movieTitle;
    @Column(name = "release_year")
    private Year releaseYear;
    @Column(name = "rating")
    @Convert(converter = String2Enum.class)
    private FilmRating rating;
    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;
//    @Enumerated(EnumType.STRING)
//    @Column(name="rating")
//    private FilmRating rating;
@ManyToMany(mappedBy = "films")
//@JoinTable(
//        name = "film_actor",
//        joinColumns = {@JoinColumn(name ="film_id")},
//        inverseJoinColumns = {@JoinColumn(name="actor_id")}
//)
private List<Actor> cast = new ArrayList<>();
    @ManyToMany()
    @JoinTable(
            name = "film_category",
            joinColumns = {@JoinColumn(name ="film_id")},
            inverseJoinColumns = {@JoinColumn(name="category_id")}
    )
    private List<Category> categories = new ArrayList<>();
}
