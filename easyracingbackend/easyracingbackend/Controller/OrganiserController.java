package com.example.easyracing.easyracingbackend.Controller;

import com.example.easyracing.easyracingbackend.Model.Event;
import com.example.easyracing.easyracingbackend.Repository.AccountRepository;
import com.example.easyracing.easyracingbackend.Repository.EventRepository;
import com.example.easyracing.easyracingbackend.Repository.OrganiserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/app/organiser")
public class OrganiserController {

    @Autowired
    OrganiserRepository organiserRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    EventRepository eventRepository;

    @CrossOrigin
    @GetMapping(value = "/events/{id}")
    public List<Event> getOrganiserEvents(@PathVariable(value = "id") int id){

        int id_organiser = organiserRepository.findByAccountId(id).getId();
        return eventRepository.findByOrganiserId(id_organiser);
    }

}
