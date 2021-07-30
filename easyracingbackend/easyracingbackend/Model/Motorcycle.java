package com.example.easyracing.easyracingbackend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Motorcycle extends Vechicle {

    public Motorcycle() {

    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToMany(mappedBy = "motorcycles")
    public List<Competition> competitions ;



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


    @Override
    public void setParameters(int capacity, int horsepower){
        this.horsepower = horsepower;
        this.capacity = capacity;
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }
}
