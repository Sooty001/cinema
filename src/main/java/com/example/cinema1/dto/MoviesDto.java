package com.example.cinema1.dto;

public class MoviesDto {
    private String title;
    private int releaseYear;
    private String genre;
    private String director;
    private int duration;
    private float rating;
    private int ageLimit;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getAgeLimit() {
        return ageLimit;
    }
    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }
}