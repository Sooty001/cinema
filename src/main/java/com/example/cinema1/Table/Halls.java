package com.example.cinema1.Table;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "залы")
public class Halls {
    private int id;
    private int seats;
    private String type;
    private int hallNumber;
    private Set<Pass> pass;


    public Halls(int seats, String type, int hallNumber) {
        this.seats = seats;
        this.type = type;
        this.hallNumber = hallNumber;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_зала")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "Количество мест")
    public int getSeats() {
        return seats;
    }
    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Column(name = "Тип зала")
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "Номер зала")
    public int getHallNumber() {
        return hallNumber;
    }
    public void setHallNumber(int hallNumber) {
        this.hallNumber = hallNumber;
    }

    @OneToMany(mappedBy = "halls", targetEntity = Pass.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Pass> getPass() { return pass; }
    public void setPass(Set<Pass> pass) { this.pass = pass; }
}
