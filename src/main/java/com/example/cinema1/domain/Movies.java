package com.example.cinema1.domain;
import com.example.cinema1.relationships.Pass;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "фильмы")
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

    public Movies() { }


    @Column(name = "Название")
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "Год_выпуска")
    public int getReleaseYear() {
        return releaseYear;
    }
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Column(name = "Жанр")
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Column(name = "Режиссер")
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }

    @Column(name = "Продолжительность")
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Column(name = "Рейтинг")
    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }

    @Column(name = "Возрастное_ограничение")
    public int getAgeLimit() {
        return ageLimit;
    }
    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    @OneToMany(mappedBy = "movies", targetEntity = Pass.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Pass> getPass() { return pass; }
    public void setPass(Set<Pass> pass) { this.pass = pass; }
}