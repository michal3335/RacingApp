package com.example.easyracing.easyracingbackend.Repository;

import com.example.easyracing.easyracingbackend.Model.Competition;
import com.example.easyracing.easyracingbackend.Model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Integer> {

    List<Result> findByCompetitionId (int competitionId);
}
