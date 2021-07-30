package com.example.easyracing.easyracingbackend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Driver extends Person {

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int id;
    @Column(name = "license")
    public String license;

    public Driver(){

    }

    public Driver(String name, String surname, int telephone, String city, Date birth, String license) {

        setName(name);
        setSurname(surname);
        setTelephone(telephone);
        setCity(city);
        setBirth(birth);
        setLicense(license);

    }

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @OneToMany(mappedBy = "driver")
    public List<Car> cars ;

    @OneToMany(mappedBy = "driver")
    public List<Motorcycle> motorcycles ;



    @ManyToMany(mappedBy = "drivers")
    public List<Competition> competitions ;


    @JsonIgnore
    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
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

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Motorcycle> getMotorcycles() {
        return motorcycles;
    }

    public void setMotorcycles(List<Motorcycle> motorcycles) {
        this.motorcycles = motorcycles;
    }
}
