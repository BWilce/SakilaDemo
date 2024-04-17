package com.example.sakilademo.repositories;

import com.example.sakilademo.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Byte> {
}
