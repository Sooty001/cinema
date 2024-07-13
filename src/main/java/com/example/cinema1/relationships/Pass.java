package com.example.cinema1.relationships;
import com.example.cinema1.domain.BaseEntityId;
import com.example.cinema1.domain.Halls;
import com.example.cinema1.domain.Movies;
import com.example.cinema1.domain.Sessions;
import jakarta.persistence.*;

@Entity
@Table(name = "проходят")
public class Pass extends BaseEntityId {
    private Halls halls;
    private Movies movies;
    private Sessions sessions;


    public Pass(Movies movies, Sessions sessions, Halls halls){
        this.movies = movies;
        this.sessions = sessions;
        this.halls = halls;
    }


    @ManyToOne(optional = false)
    @JoinColumn(name = "id_зала", referencedColumnName = "id")
    public Halls getHalls() {
        return halls;
    }
    public void setHalls(Halls halls) {
        this.halls = halls;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_фильма", referencedColumnName = "id")
    public Movies getMovies() {
        return movies;
    }
    public void setMovies(Movies movies) {
        this.movies = movies;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_сеанса", referencedColumnName = "id")
    public Sessions getSessions() {
        return sessions;
    }
    public void setSessions(Sessions sessions) {
        this.sessions = sessions;
    }
}