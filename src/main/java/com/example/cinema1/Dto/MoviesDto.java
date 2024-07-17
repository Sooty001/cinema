package com.example.cinema1.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoviesDto {
    //private int id;
    private String title;
    private int releaseYear;
    private String genre;
    private String director;
    private int duration;
    private float rating;
    private int ageLimit;
}
