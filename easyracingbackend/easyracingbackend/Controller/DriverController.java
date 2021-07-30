package com.example.easyracing.easyracingbackend.Controller;

import com.example.easyracing.easyracingbackend.Model.Competition;
import com.example.easyracing.easyracingbackend.Model.Driver;
import com.example.easyracing.easyracingbackend.Model.Event;
import com.example.easyracing.easyracingbackend.Repository.AccountRepository;
import com.example.easyracing.easyracingbackend.Repository.CompetitionRepository;
import com.example.easyracing.easyracingbackend.Repository.DriverRepository;
import com.example.easyracing.easyracingbackend.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/app/driver")
public class DriverController {


    @Autowired
    DriverRepository driverRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CompetitionRepository competitionRepository;
    @Autowired
    EventRepository eventRepository;


    @CrossOrigin
    @PostMapping(value = "/join")
    public void join(@RequestParam int id, @RequestParam String email){

        Competition comp = competitionRepository.getOne(id);
       int id_account =  accountRepository.checkAccountId(email);
       try {
           comp.addDriver(driverRepository.findByAccountId(id_account));
       }catch(Exception e ){
           System.out.print(e);
       }

       competitionRepository.save(comp);

    }

    @CrossOrigin
    @DeleteMapping(value = "/cancel/{id}/{id_account}")
    public void cancelJoin(@PathVariable int id, @PathVariable int id_account){
       Competition comp = competitionRepository.getOne(id);
        Driver d = driverRepository.findByAccountId(id_account);
       comp.getDrivers().remove(d);
       competitionRepository.save(comp);

    }


    @CrossOrigin
    @GetMapping(value = "/event/{id}")
    public List<Event> getDriverEvents(@PathVariable(value = "id") int id){

        int id_driver = driverRepository.findByAccountId(id).getId();
        List<Competition> comps = competitionRepository.findByDriversId(id_driver);
        List<Event> events = new ArrayList<>();

        for (Competition c: comps) {
           events.add( eventRepository.findByCompetitionsId(c.getId()));
        }
        return events;
    }
}
