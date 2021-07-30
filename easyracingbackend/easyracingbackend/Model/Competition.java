package com.example.easyracing.easyracingbackend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity
    public class Competition {

        protected   Competition() {
    }
        public  Competition(Event event, String name, String rules, String distance) {
            setName(name);
            setRules(rules);
            setDistance(distance);
            setEvent(event);
    }
        @Id
        @GeneratedValue
        @Column(name = "id")
        public int id;
        @Column(name = "name")
        public String name;
        @Column(name = "rules")
        public String rules;
        @Column(name = "distance")
        public String distance;

        @ManyToOne(cascade = CascadeType.REFRESH)
        @JoinColumn(name = "event_id")
        private Event event;

        @ManyToMany(cascade = CascadeType.PERSIST)
        @JoinTable
        public List<Driver> drivers;

        @OneToMany(mappedBy = "competition")
        public List<Result> results ;

        @JsonIgnore
        @ManyToMany(cascade = CascadeType.PERSIST)
        @JoinTable
        public List<Car> cars;

        @JsonIgnore
        @ManyToMany(cascade = CascadeType.PERSIST)
        @JoinTable
        public List<Motorcycle> motorcycles;

        public List<Result> getResults() {
        return results;
        }

        public void setResults(List<Result> results) {
        this.results = results;
        }

        @JsonIgnore
        public Event getEvent() {
            return event;
        }

        public void setEvent(Event event) { this.event  = event;}

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRules() {
            return rules;
        }

        public void setRules(String rules) {
            this.rules = rules;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

    @JsonIgnore
    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public void addDriver(Driver driver)throws Exception{
            if(!drivers.contains(driver)){
                drivers.add(driver);
            }else{
                throw new Exception("Driver already joined");

            }
    }

    public void addMotorcycle(Motorcycle motorcycle)throws Exception{
        if(!motorcycles.contains(motorcycle)){
            motorcycles.add(motorcycle);
        }else{
            throw new Exception("Motorcycle already joined");

        }
    }

    public void addCar(Car car)throws Exception{
        if(!cars.contains(car)){
            cars.add(car);
        }else{
            throw new Exception("Car already joined");

        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Competition that = (Competition) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(rules, that.rules) &&
                Objects.equals(distance, that.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rules, distance);
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
