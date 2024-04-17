package com.example.sakilademo.dto.input;

import com.example.sakilademo.entities.Language;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Year;

import static com.example.sakilademo.dto.input.ValidationGroup.Create;
@Data
public class FilmInput {
    @NotNull(groups = {Create.class})
    @Size(min = 1, max = 128)
    private String movieTitle;
    @NotNull(groups = {Create.class})
    private Year releaseYear;
    @NotNull(groups = {Create.class})
    private Byte languageID;
//    @NotNull(groups = {Create.class})
//    private BigDecimal rentalRate;
}
