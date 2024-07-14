package com.example.cinema1.domain;
import com.example.cinema1.relationships.Purchase;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "пользователи")
public class Users extends BaseEntityId{
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private String telephone;
    private int age;
    private Set<Purchase> purchase;


    public Users(String last_name, String first_name, String patronymic, String email, String telephone, int age){
        this.firstName = first_name;
        this.lastName = last_name;
        this.patronymic = patronymic;
        this.email = email;
        this.telephone = telephone;
        this.age = age;
    }

    public Users() { }


    @Column(name = "Имя")
    public String getFirst_name() { return firstName; }
    public void setFirst_name(String first_name) { this.firstName = firstName; }

    @Column(name = "Фамилия")
    public String getLast_name() { return lastName; }
    public void setLast_name(String last_name) { this.lastName = last_name; }

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