package com.example.easyracing.easyracingbackend.Repository;

import com.example.easyracing.easyracingbackend.Model.Organiser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganiserRepository extends JpaRepository<Organiser, Integer> {

    Organiser findByAccountId(int id);
}
