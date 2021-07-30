package com.example.easyracing.easyracingbackend.Model;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Vechicle {

    @Column(name = "brand")
    public String brand;
    @Column(name = "model")
    public String model;
    @Column(name = "year")
    public int year;
    @Column(name = "capacity")
    public int capacity;
    @Column(name = "horsepower")
    public int horsepower;
    @Column(name = "homologation")
    public String homologation;
    @Lob
    @Column(name = "photo", columnDefinition="longblob")
    public byte[]  photo;

    abstract void setParameters(int i, int j);


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public String getHomologation() {
        return homologation;
    }

    public void setHomologation(String homologation) {
        this.homologation = homologation;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
