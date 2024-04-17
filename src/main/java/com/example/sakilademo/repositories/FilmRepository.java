package com.example.sakilademo.repositories;
import com.example.sakilademo.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface FilmRepository extends JpaRepository<Film, Short> {
}
