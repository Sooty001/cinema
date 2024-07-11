package com.example.cinema1.Table;
import jakarta.persistence.*;

@Entity
@Table(name = "проходят")
public class Pass {
    private int id;
    private Halls halls;
    private Movies movies;
    private Sessions sessions;


    public Pass(Movies movies, Sessions sessions, Halls halls){
        this.movies = movies;
        this.sessions = sessions;
        this.halls = halls;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_зала", referencedColumnName = "id_зала")
    public Halls getHalls() {
        return halls;
    }
    public void setHalls(Halls halls) {
        this.halls = halls;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_фильма", referencedColumnName = "id_фильма")
    public Movies getMovies() {
        return movies;
    }
    public void setMovies(Movies movies) {
        this.movies = movies;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_сеанса", referencedColumnName = "id_сеанса")
    public Sessions getSessions() {
        return sessions;
    }
    public void setSessions(Sessions sessions) {
        this.sessions = sessions;
    }
}