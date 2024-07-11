package com.example.cinema1.Table;
import jakarta.persistence.*;

@Entity
@Table(name = "покупка")
public class Purchase {
    private int id;
    private Users users;
    private Sessions sessions;
    private Tickets tickets;


    public Purchase(Users users, Sessions sessions, Tickets tickets){
        this.users = users;
        this.sessions = sessions;
        this.tickets = tickets;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_пользователя", referencedColumnName = "id_пользователя")
    public Users getUsers() {
        return users;
    }
    public void setUsers(Users users) {
        this.users = users;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_сеанса", referencedColumnName = "id_сеанса")
    public Sessions getSessions() {
        return sessions;
    }
    public void setSessions(Sessions sessions) {
        this.sessions = sessions;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_билета", referencedColumnName = "id_билета")
    public Tickets getTickets() {
        return tickets;
    }
    public void setTickets(Tickets tickets) {
        this.tickets = tickets;
    }
}

