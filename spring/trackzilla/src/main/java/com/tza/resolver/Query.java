package com.tza.resolver;

import com.tza.entity.Release;
import com.tza.repository.ReleaseRepository;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.tza.entity.Application;
import com.tza.entity.Ticket;
import com.tza.repository.ApplicationRepository;
import com.tza.repository.TicketRepository;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {
    private ApplicationRepository applicationRepository;
    private TicketRepository ticketRepository;
    private ReleaseRepository releaseRepository;

    public Query(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public Iterable<Application> findAllApplications() {
        return applicationRepository.findAll();
    }

    public Iterable<Ticket> findAllTickets() { return ticketRepository.findAll(); }

    public Iterable<Release> findAllRelease() { return releaseRepository.findAll(); }

    public long countApplications() {
        return applicationRepository.count();
    }
}
