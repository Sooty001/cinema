package com.example.cinema1.Table;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "пользователи")
public class Users {
    private int id;
    private String first_name;
    private String last_name;
    private String patronymic;
    private String email;
    private String telephone;
    private int age;
    private Set<Purchase> purchase;


    public Users(String last_name, String first_name, String patronymic, String email, String telephone, int age){
        this.first_name = first_name;
        this.last_name = last_name;
        this.patronymic = patronymic;
        this.email = email;
        this.telephone = telephone;
        this.age = age;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_пользователя")
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    @Column(name = "Имя")
    public String getFirst_name() { return first_name; }
    public void setFirst_name(String first_name) { this.first_name = first_name; }

    @Column(name = "Фамилия")
    public String getLast_name() { return last_name; }
    public void setLast_name(String last_name) { this.last_name = last_name; }

    @Column(name = "Отчество")
    public String getPatronymic() { return patronymic; }
    public void setPatronymic(String patronymic) { this.patronymic = patronymic; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Column(name = "Телефон")
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    @Column(name = "Возраст")
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    @OneToMany(mappedBy = "users", targetEntity = Purchase.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Purchase> getPurchase() { return purchase; }
    public void setPurchase(Set<Purchase> purchase) { this.purchase = purchase; }
}