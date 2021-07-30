package com.example.easyracing.easyracingbackend.Repository;

import com.example.easyracing.easyracingbackend.Model.Car;
import com.example.easyracing.easyracingbackend.Model.Event;
import com.example.easyracing.easyracingbackend.Model.Motorcycle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MotorcycleRepository extends JpaRepository<Motorcycle, Integer> {

    List<Motorcycle> findByDriverId(int id);
}
