package com.example.cinema1.domain;
import com.example.cinema1.relationships.Pass;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "залы")
public class Halls extends BaseEntityId{
    private int seats;
    private String type;
    private int hallNumber;
    private Set<Pass> pass;


    public Halls(int seats, String type, int hallNumber) {
        this.seats = seats;
        this.type = type;
        this.hallNumber = hallNumber;
    }

    public Halls() {

    }


    @Column(name = "Количество_мест")
    public int getSeats() {
        return seats;
    }
    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Column(name = "Тип_зала")
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "Номер_зала")
    public int getHallNumber() {
        return hallNumber;
    }
    public void setHallNumber(int hallNumber) {
        this.hallNumber = hallNumber;
    }

    @OneToMany(mappedBy = "halls", targetEntity = Pass.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    public Set<Pass> getPass() { return pass; }
    public void setPass(Set<Pass> pass) { this.pass = pass; }
}
