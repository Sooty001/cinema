package com.example.cinema1.domain;

import com.example.cinema1.relationships.Pass;
import com.example.cinema1.relationships.Purchase;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "сеансы")
public class Sessions extends BaseEntityId{
    private String start_time;
    private Date date;
    private Set<Pass> pass;
    private Set<Purchase> purchase;


    public Sessions(String start_time, Date date) {
        this.start_time = start_time;
        this.date = date;
    }

    public Sessions() { }


    @Column(name = "Время_начала")
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