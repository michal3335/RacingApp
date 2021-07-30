package com.example.easyracing.easyracingbackend.Repository;

import com.example.easyracing.easyracingbackend.Model.Competition;
import com.example.easyracing.easyracingbackend.Model.Driver;
import com.example.easyracing.easyracingbackend.Model.Organiser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface DriverRepository extends JpaRepository<Driver, Integer> {

    Driver findByAccountId(int id);
    List<Driver> findByCompetitionsId(int id);


    }
