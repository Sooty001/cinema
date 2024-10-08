package com.example.cinema1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pass")
public class Pass extends BaseEntityId {
    private Halls halls;
    private Movies movies;
    private Sessions sessions;

    public Pass(Movies movies, Sessions sessions, Halls halls){
        this.movies = movies;
        this.sessions = sessions;
        this.halls = halls;
    }

    protected Pass() {
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_hall", referencedColumnName = "id")
    public Halls getHalls() {
        return halls;
    }
    public void setHalls(Halls halls) {
        this.halls = halls;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_movies", referencedColumnName = "id")
    public Movies getMovies() {
        return movies;
    }
    public void setMovies(Movies movies) {
        this.movies = movies;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_session", referencedColumnName = "id")
    public Sessions getSessions() {
        return sessions;
    }
    public void setSessions(Sessions sessions) {
        this.sessions = sessions;
    }
}