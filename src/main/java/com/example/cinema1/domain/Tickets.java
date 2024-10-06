package com.example.cinema1.domain;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "tickets")
public class Tickets extends BaseEntityId{
    private float price;
    private String status;
    private String place;
    private LocalTime choice;
    private Purchase purchase;

    public Tickets(float price, String status, String place){
        this.price = price;
        this.status = status;
        this.place = place;
    }

    protected Tickets() { }

    @Column(name = "price")
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    @Column(name = "status")
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "place")
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }

    @Column(name = "choice")
    public LocalTime getChoice() {
        return choice;
    }
    public void setChoice(LocalTime choice) {
        this.choice = choice;
    }

    @OneToOne(mappedBy = "tickets")
    public Purchase getPurchase() {
        return purchase;
    }
    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }
}

