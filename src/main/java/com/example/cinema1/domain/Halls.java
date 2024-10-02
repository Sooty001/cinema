package com.example.cinema1.domain;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "halls")
public class Halls extends BaseEntityId {
    private int seats;
    private String type;
    private int hallNumber;
    private Set<Pass> pass;

    public Halls(int seats, String type, int hallNumber) {
        this.seats = seats;
        this.type = type;
        this.hallNumber = hallNumber;
    }

    protected Halls() {
    }

    @Column(name = "seats")
    public int getSeats() {
        return seats;
    }
    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "hall_number")
    public int getHallNumber() {
        return hallNumber;
    }
    public void setHallNumber(int hallNumber) {
        this.hallNumber = hallNumber;
    }

    @OneToMany(mappedBy = "halls", targetEntity = Pass.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Pass> getPass() {
        return pass;
    }
    public void setPass(Set<Pass> pass) {
        this.pass = pass;
    }
}
