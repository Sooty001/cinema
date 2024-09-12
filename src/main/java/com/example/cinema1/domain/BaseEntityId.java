package com.example.cinema1.domain;
import jakarta.persistence.*;
import lombok.Setter;

@Setter
@MappedSuperclass
public abstract class BaseEntityId {
    private int id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

}