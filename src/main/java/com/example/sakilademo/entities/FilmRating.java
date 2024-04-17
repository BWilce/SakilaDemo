package com.example.sakilademo.entities;

public enum FilmRating {
    G, PG, PG_13, R, NC_17;
    public static FilmRating ratingData(String value){
        switch (value){
            case "G":
                return G;
            case "PG":
                return PG;
            case "PG-13":
                return PG_13;
            case "R":
                return R;
            case "NC-17":
                return NC_17;
            default:
                throw new IllegalArgumentException("Unrecognised film rating: "+value);
        }
    }
}
