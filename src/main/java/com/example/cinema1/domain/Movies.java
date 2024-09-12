package com.example.cinema1.domain;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movies extends BaseEntityId{
    private String title;
    private int releaseYear;
    private String genre;
    private String director;
    private int duration;
    private float rating;
    private int ageLimit;
    private Set<Pass> pass;


    public Movies(String title, int releaseYear, String genre, String director, int duration, float rating, int ageLimit){
        this.title = title;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.director = director;
        this.duration = duration;
        this.rating = rating;
        this.ageLimit = ageLimit;
    }

    protected Movies() { }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "release_year")
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

    @Column(name = "age_limit")
    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    @OneToMany(mappedBy = "movies", targetEntity = Pass.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Pass> getPass() {
        return pass;
    }

    public void setPass(Set<Pass> pass) {
        this.pass = pass;
    }
}