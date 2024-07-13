package com.example.cinema1.domain;
import com.example.cinema1.relationships.Purchase;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "билеты")
public class Tickets extends BaseEntityId{
    private float price;
    private String status;
    private String place;
    private Set<Purchase> purchase;


    public Tickets(float price, String status, String place){
        this.price = price;
        this.status = status;
        this.place = place;
    }

    protected Tickets() { }


    @Column(name = "Цена")
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    @Column(name = "Статус")
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "Место")
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }

    @OneToMany(mappedBy = "tickets", targetEntity = Purchase.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Purchase> getPurchase() { return purchase; }
    public void setPurchase(Set<Purchase> purchase) { this.purchase = purchase; }
}