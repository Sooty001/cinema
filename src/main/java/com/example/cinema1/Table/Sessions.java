package com.example.cinema1.Table;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "сеансы")
public class Sessions {
    private int id;
    private String start_time;
    private Date date;
    private Set<Pass> pass;
    private Set<Purchase> purchase;


    public Sessions(String start_time, Date date) {
        this.start_time = start_time;
        this.date = date;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_сеанса")
    public int getId() { return id; }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "Время начала")
    public String getStart_time() { return start_time; }
    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    @Column(name = "Дата")
    public Date getDate() { return date; }
    public void setDate(Date date) {
        this.date = date;
    }

    @OneToMany(mappedBy = "sessions", targetEntity = Pass.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Pass> getPass() { return pass; }
    public void setPass(Set<Pass> pass) { this.pass = pass; }

    @OneToMany(mappedBy = "sessions", targetEntity = Purchase.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Purchase> getPurchase() { return purchase; }
    public void setPurchase(Set<Purchase> purchase) { this.purchase = purchase; }
}