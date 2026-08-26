package com.chacuio.issueflowapi.tickets.repository;

import com.chacuio.issueflowapi.tickets.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> { }
