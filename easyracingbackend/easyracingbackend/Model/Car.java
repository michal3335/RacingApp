package com.example.easyracing.easyracingbackend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Car extends Vechicle {

    public Car() {

    }
    @Id
    @GeneratedValue
    @Column(name = "id")
    public int id;

    @Column(name = "fuel")
    public String fuel;


    @JsonIgnore

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToMany(mappedBy = "cars")
    public List<Competition> competitions ;


    @Override
    public void setParameters(int capacity, int horsepower){
        this.horsepower = horsepower;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }
}
