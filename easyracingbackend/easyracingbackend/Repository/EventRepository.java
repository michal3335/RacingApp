package com.example.easyracing.easyracingbackend.Repository;

import com.example.easyracing.easyracingbackend.Model.Competition;
import com.example.easyracing.easyracingbackend.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findByOrganiserId(int id);
    Event findByCompetitionsId(int id);



}
