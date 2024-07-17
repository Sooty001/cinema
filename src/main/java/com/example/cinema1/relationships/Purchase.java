package com.example.cinema1.relationships;
import com.example.cinema1.domain.BaseEntityId;
import com.example.cinema1.domain.Sessions;
import com.example.cinema1.domain.Tickets;
import com.example.cinema1.domain.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "покупка")
public class Purchase extends BaseEntityId {
    private Users users;
    private Sessions sessions;
    private Tickets tickets;


    public Purchase(Users users, Sessions sessions, Tickets tickets){
        this.users = users;
        this.sessions = sessions;
        this.tickets = tickets;
    }

    public Purchase() {

    }


    @ManyToOne(optional = false)
    @JoinColumn(name = "id_пользователя", referencedColumnName = "id")
    public Users getUsers() {
        return users;
    }
    public void setUsers(Users users) {
        this.users = users;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_сеанса", referencedColumnName = "id")
    public Sessions getSessions() {
        return sessions;
    }
    public void setSessions(Sessions sessions) {
        this.sessions = sessions;
    }


    @OneToOne
    @JoinColumn(name = "id_билета")
    public Tickets getTickets() {
        return tickets;
    }
    public void setTickets(Tickets tickets) {
        this.tickets = tickets;
    }

}

