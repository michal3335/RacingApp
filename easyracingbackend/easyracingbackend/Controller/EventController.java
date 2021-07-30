package com.example.easyracing.easyracingbackend.Controller;


import com.example.easyracing.easyracingbackend.Model.*;
import com.example.easyracing.easyracingbackend.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value="/app/event")
public class EventController {


    @Autowired
    EventRepository eventRepository;

    @Autowired
    CompetitionRepository competitionRepository;

    @Autowired
    OrganiserRepository organiserRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ResultRepository resultRepository;

    @CrossOrigin
    @GetMapping(value = "/all")
    public List<Event> getAll(){
        return eventRepository.findAll();
    }



    @CrossOrigin
    @GetMapping(value = "/competitions")
    public List<Competition> getCompetitions(){
        return competitionRepository.findAll();
    }


    @CrossOrigin
    @GetMapping("/{id}/competitions")
    public List < Competition > getCompetitionsByEvent(@PathVariable(value = "id") int id) {
        return competitionRepository.findByEventId(id);
    }


    @CrossOrigin
    @GetMapping("/{id}/results")
    public List <Result> getCompetitionResults(@PathVariable(value = "id") int id) {
        return resultRepository.findByCompetitionId(id);
    }

    @CrossOrigin
    @GetMapping(value = "/al/{id}")
    public byte[] getImage(@PathVariable int id) throws Exception{
        byte[] b  =  eventRepository.getOne(id).getTrack();

        return  b;
    }

    @CrossOrigin
    @GetMapping(value = "/one/{id}")
    public Optional<Event> getEvent(@PathVariable int id){


        return eventRepository.findById(id);
    }

    @CrossOrigin
    @DeleteMapping(value = "/delete/{id}")
    public void deleteEvent(@PathVariable int id){

        Event e = eventRepository.getOne(id);
        eventRepository.delete(e);
    }

    @CrossOrigin
    @DeleteMapping(value = "/competition/delete/{id}")
    public void deleteCompetition(@PathVariable int id){

        Competition c = competitionRepository.getOne(id);
        competitionRepository.delete(c);
    }

    @CrossOrigin
    @DeleteMapping(value = "/result/delete/{id}")
    public void deleteResult(@PathVariable int id){

        Result r = resultRepository.getOne(id);
        resultRepository.delete(r);
    }



    @CrossOrigin
    @GetMapping(value="/competition/drivers/{id}")
    public List <Driver> getDriversByCompetition(@PathVariable(value = "id") int id) {

        return driverRepository.findByCompetitionsId(id);
    }



    @CrossOrigin
    @PostMapping(value = "/add")
    public void persistEvent(@RequestParam("track") MultipartFile track,
                             @RequestParam("name") String name,
                             @RequestParam("localization") String localization,
                             @RequestParam("date") String date,
                             @RequestParam("description") String description,
                             @RequestParam("email") String email)
    {

        Event event = new Event();
        event.setName(name);
        event.setLocalization(localization);
        event.setDescription(description);


        try {
            Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(date);
            event.setDate(date1);
        }catch (Exception e){
            System.out.println(e);
        }

        try {
           InputStream inputStream =  track.getInputStream();
            byte[] bytes = inputStream.readAllBytes();
            event.track = bytes;
        }catch (Exception e){
            System.out.println(e);

        }


       Account a =  accountRepository.findOneByEmail(email);
        event.setOrganiser(organiserRepository.findByAccountId(a.getId()));
       eventRepository.save(event);


    }

    @CrossOrigin
    @PostMapping(value = "/add/competition")
    public void persistCompetition(
                             @RequestParam("name") String name,
                             @RequestParam("rules") String rules,
                             @RequestParam("distance") String distance,
                             @RequestParam("id") int id)
    {

        Event event  = eventRepository.getOne(id);
        Competition comp = new Competition(event,name,rules,distance);
        competitionRepository.save(comp);


    }

    @CrossOrigin
    @PostMapping(value = "/add/result")
    public void persistResult(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("time") String time,
            @RequestParam("score") String score,
            @RequestParam("id") int id)
    {

        Competition comp = competitionRepository.getOne(id);
        Result result = new Result(name,surname,time,score);
        result.setCompetition(comp);
        resultRepository.save(result);


    }

}

