package com.example.easyracing.easyracingbackend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Result {

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int id;
    @Column(name = "name")
    public String name;
    @Column(name = "surname")
    public String surname;
    @Column(name = "time")
    public String time;
    @Column(name = "score")
    public String score;

    public Result(){};

    public Result(String name, String surname, String time, String score){
        setName(name);
        setSurname(surname);
        setTime(time);
        setScore(score);
    }

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "competition_id")
    private Competition competition;

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
