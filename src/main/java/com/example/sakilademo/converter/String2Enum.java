package com.example.sakilademo.converter;

import com.example.sakilademo.entities.FilmRating;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class String2Enum implements AttributeConverter<FilmRating,String> {
    @Override
    public String convertToDatabaseColumn(FilmRating rating){
        if (rating == null){
            return null;
        }

        return rating.name().replace("_","-");
    }
    @Override
    public FilmRating convertToEntityAttribute(String databaseData){
        if (databaseData == null){
            return null;
        }
        return FilmRating.ratingData(databaseData);
    }
}
