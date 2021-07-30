package com.example.easyracing.easyracingbackend.Repository;
import com.example.easyracing.easyracingbackend.Model.Fan;
import org.springframework.data.jpa.repository.JpaRepository;



public interface FanRepository extends JpaRepository<Fan, Integer> {

    Fan findByAccountId(int id);


}
