package com.example.cinema1.domain;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "purchase")
public class Purchase extends BaseEntityId {
    private Users users;
    private Sessions sessions;
    private Tickets tickets;

    public Purchase(Users users, Sessions sessions, Tickets tickets){
        this.users = users;
        this.sessions = sessions;
        this.tickets = tickets;
    }

    protected Purchase() {
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    public Users getUsers() {
        return users;
    }
    public void setUsers(Users users) {
        this.users = users;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_session", referencedColumnName = "id")
    public Sessions getSessions() {
        return sessions;
    }
    public void setSessions(Sessions sessions) {
        this.sessions = sessions;
    }

    @OneToOne
    @JoinColumn(name = "id_ticket")
    public Tickets getTickets() {
        return tickets;
    }
    public void setTickets(Tickets tickets) {
        this.tickets = tickets;
    }

}

