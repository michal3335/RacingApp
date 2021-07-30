package com.example.easyracing.easyracingbackend.Model;

import org.aspectj.weaver.ast.Or;

import javax.persistence.*;

@Entity
public class Account {

    public Account(){

    }
    public Account(String email, String password, String role){
        setEmail(email);
        setPassword(password);
        setRole(role);
    }
    @Id
    @GeneratedValue
    @Column(name = "id")
    public int id;
    @Column(name = "email")
    public String email;
    @Column(name = "password")
    public String password;
    @Column(name = "role")
    public String role;

    @OneToOne(mappedBy = "account")
    private Driver driver;

    @OneToOne(mappedBy = "account")
    private Organiser organiser;

    @OneToOne(mappedBy = "account")
    private Fan fan;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
