package com.tza.service;

import com.tza.entity.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> listTickets();
    Ticket findTicket(Integer id);
}


