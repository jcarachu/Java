package com.tza.web;

import com.tza.entity.Application;
import com.tza.entity.Release;
import com.tza.entity.Ticket;
import com.tza.exception.ApplicationNotFoundException;
import com.tza.exception.ReleaseNotFoundException;
import com.tza.exception.TicketNotFoundException;
import com.tza.service.ApplicationService;
import com.tza.service.ReleaseService;
import com.tza.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/tza")
public class APIController {
    private ApplicationService applicationService;
    private TicketService ticketService;
    private ReleaseService releaseService;

    @Autowired
    public void setApplicationService(ApplicationService applicationService) { this.applicationService = applicationService; }

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Autowired
    public void setReleaseService(ReleaseService releaseService) { this.releaseService = releaseService; }

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> list = ticketService.listTickets();
        return new ResponseEntity<List<Ticket>>(list, HttpStatus.OK);
    }

    @GetMapping("/applications")
    public ResponseEntity<List<Application>> getAllApplications() {
        List<Application> list = applicationService.listApplications();
        return new ResponseEntity<List<Application>>(list, HttpStatus.OK);
    }

    @GetMapping("/releases")
    public ResponseEntity<List<Release>> getAllRelease() {
        List<Release> list = releaseService.listReleases();
        return new ResponseEntity<List<Release>>(list, HttpStatus.OK);
    }

    @GetMapping("/applications/{id}")
    public ResponseEntity<Application> getApplication(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<Application>(applicationService.findApplication(id), HttpStatus.OK);
        } catch (ApplicationNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Application Not Found");
        }
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable("id") Integer id)
    {
        try {
            return new ResponseEntity<Ticket>(ticketService.findTicket(id), HttpStatus.OK);
        } catch(TicketNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket Not Found");
        }

    }

    @GetMapping("/releases/{id}")
    public ResponseEntity<Release> getRelease(@PathVariable("id") Integer id)
    {
        try {
            return new ResponseEntity<Release>(releaseService.findRelease(id), HttpStatus.OK);
        } catch(ReleaseNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Release Not Found");
        }
    }
}