package com.example.easyracing.easyracingbackend.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Fan extends Person {

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int id;

    public Fan(String name, String surname, int telephone, String city, Date birth) {

        setName(name);
        setSurname(surname);
        setTelephone(telephone);
        setCity(city);
        setBirth(birth);

    }

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
