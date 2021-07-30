package com.example.easyracing.easyracingbackend.Controller;

import com.example.easyracing.easyracingbackend.Dto.UserData;
import com.example.easyracing.easyracingbackend.Model.*;
import com.example.easyracing.easyracingbackend.Repository.AccountRepository;
import com.example.easyracing.easyracingbackend.Repository.DriverRepository;
import com.example.easyracing.easyracingbackend.Repository.FanRepository;
import com.example.easyracing.easyracingbackend.Repository.OrganiserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/user")
public class UserController {


    @Autowired
    DriverRepository driverRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    OrganiserRepository organiserRepository;
    @Autowired
    FanRepository fanRepository;


    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Autowired
    public UserController(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }

    @CrossOrigin
    @GetMapping(value = "/all")
    public List<Account> getAll(){
        return accountRepository.findAll();
    }

    @CrossOrigin
    @GetMapping(value = "/authenticate")
    public UserData authenticate(@RequestParam String email, @RequestParam String password) throws Exception{


       String pass =  accountRepository.checkAccountPassword(email);

       if(pass.equals(password)) {

           if(!inMemoryUserDetailsManager.userExists(email)){

               String role = accountRepository.checkAccountRole(email);


               List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
               authorities.add(new SimpleGrantedAuthority(role));
               inMemoryUserDetailsManager.createUser(new User(email, passwordEncoder.encode(password),authorities));
               System.out.println("Dodano nowego uprawnionego użytkownika");

           }

           UserData user = new UserData();
           user.setId(accountRepository.checkAccountId(email));
           user.setRole(accountRepository.checkAccountRole(email));
           return user;
       }
       else{
           throw new Exception();
       }

    }

    @CrossOrigin
    @PostMapping(value = "/add")
    public void addAccount(@RequestBody final UserData data) throws Exception {

        List<Account> accounts = new ArrayList<>();
        accounts = accountRepository.findByEmail(data.getEmail());

        if(accounts.isEmpty()){

            Account account = new Account(data.getEmail(),data.getPassword(),data.getRole());



            if(data.getRole().toLowerCase().equals("driver")){

                accountRepository.save(account);
                Driver driver = new Driver(data.name,data.surname,data.telephone,data.city,data.birth,data.license);
                driver.setAccount(account);
                driverRepository.save(driver);

            }else if (data.getRole().toLowerCase().equals("organiser")){

                accountRepository.save(account);
                Organiser organiser = new Organiser(data.name,data.surname,data.telephone,data.city,data.birth,data.permission);
                organiser.setAccount(account);
                organiserRepository.save(organiser);

            }else if(data.getRole().toLowerCase().equals("fan")){
                accountRepository.save(account);
                Fan fan = new Fan(data.name,data.surname,data.telephone,data.city,data.birth);
                fan.setAccount(account);
                fanRepository.save(fan);
            }
            else{
                throw new Exception("zła rola");
            }

        }else{
            throw new Exception("Istnieje juz uzytkownik z takim email");
            }

        }
    }

