package com.example.easyracing.easyracingbackend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Organiser extends Person {

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int id;
    public String permission;

    public Organiser(){

    }

    public Organiser(String name, String surname, int telephone, String city, Date birth, String permission) {

        setName(name);
        setSurname(surname);
        setTelephone(telephone);
        setCity(city);
        setBirth(birth);
        setPermission(permission);

    }

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @OneToMany(mappedBy = "organiser")
    public Set<Event> events ;

    @JsonIgnore
    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Organiser(String name,  String permission) {

        setName(name);
        setPermission(permission);
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
