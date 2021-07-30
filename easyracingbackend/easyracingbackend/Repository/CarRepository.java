package com.example.easyracing.easyracingbackend.Repository;

import com.example.easyracing.easyracingbackend.Model.Car;
import com.example.easyracing.easyracingbackend.Model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Integer> {

        List<Car> findByDriverId(int id);

}
