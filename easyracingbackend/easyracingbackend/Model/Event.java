package com.example.easyracing.easyracingbackend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
public class Event {

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int id;
    @Column(name = "name")
    public String name;
    @Column(name = "localization")
    public String localization;
    @Column(name = "description")
    public String description;
    @Column(name="date")
    public Date date;
    @Lob
    @Column(name = "track", columnDefinition="longblob")
    public byte[]  track;


    @OneToMany(mappedBy = "event",cascade = {
            CascadeType.REMOVE
    })
    private List< Competition > competitions;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "organiser_id")
    private Organiser organiser;

    @JsonIgnore
    public Organiser getOrganiser() {
        return organiser;
    }

    public void setOrganiser(Organiser organiser) {
        this.organiser = organiser;
    }

    @JsonIgnore
    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }

    public void addCompetition(Competition c) throws Exception{
        if(!competitions.contains(c)){
            competitions.add(c);
        }else{
            throw new Exception("Istnieje już taka konkurencja w tym wydarzeniu");
        }
    }


    public byte[] getTrack() {
        return track;
    }

    public void setTrack(byte[] track) {
        this.track = track;
    }

    public Event() {
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

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
