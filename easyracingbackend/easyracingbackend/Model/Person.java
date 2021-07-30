package com.example.easyracing.easyracingbackend.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@MappedSuperclass
public abstract class Person {



    @Column(name = "name")
    public String name;
    @Column(name = "surname")
    public String surname;
    @Column(name = "telephone")
    public int telephone;
    @Column(name = "city")
    public String city;
    @Column(name = "birth")
    public Date birth;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }


}
