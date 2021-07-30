package com.example.easyracing.easyracingbackend.Repository;

import com.example.easyracing.easyracingbackend.Model.Account;
import com.example.easyracing.easyracingbackend.Model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {


    @Query("SELECT a.password FROM Account a where a.email = :email")
    String checkAccountPassword(@Param("email") String email);


    @Query("SELECT a.role FROM Account a where a.email = :email")
    String checkAccountRole(@Param("email") String email);

    @Query("SELECT a.id FROM Account a where a.email = :email")
    int checkAccountId(@Param("email") String email);

    List<Account> findByEmail(String email);

    Account findOneByEmail(String email);
}
