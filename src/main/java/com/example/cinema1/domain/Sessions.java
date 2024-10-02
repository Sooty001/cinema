package com.example.cinema1.domain;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "sessions")
public class Sessions extends BaseEntityId{
    private String startTime;
    private Date date;
    private Set<Pass> pass;
    private Set<Purchase> purchase;

    public Sessions(String start_time, Date date) {
        this.startTime = start_time;
        this.date = date;
    }

    protected Sessions() { }

    @Column (name = "start_time")
    public String getStart_time() { return startTime; }
    public void setStart_time(String start_time) {
        this.startTime = startTime;
    }

    @Column(name = "date")
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