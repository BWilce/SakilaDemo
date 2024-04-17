package com.example.sakilademo.repositories;
import com.example.sakilademo.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends JpaRepository<Actor, Short> {
}
