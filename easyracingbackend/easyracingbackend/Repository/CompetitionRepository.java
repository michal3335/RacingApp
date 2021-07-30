package com.example.easyracing.easyracingbackend.Repository;

import com.example.easyracing.easyracingbackend.Model.Competition;
import com.example.easyracing.easyracingbackend.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompetitionRepository extends JpaRepository<Competition, Integer> {

    List<Competition> findByEventId (int eventId);

    List<Competition> findByDriversId(int id);


}
