package com.chacuio.issueflowapi.tickets.service;

import com.chacuio.issueflowapi.tickets.dto.AssignTicketRequestDTO;
import com.chacuio.issueflowapi.tickets.dto.ChangeTicketStateDTO;
import com.chacuio.issueflowapi.tickets.dto.TicketDTO;
import com.chacuio.issueflowapi.tickets.dto.TicketRequestDTO;

import java.util.List;
import java.util.UUID;

public interface TicketService {
    TicketDTO create(TicketRequestDTO dto);
    List<TicketDTO> findAll();
    TicketDTO findById(UUID id);
    TicketDTO assign(UUID ticketId, AssignTicketRequestDTO dto, UUID assignerId);
    TicketDTO changeState(UUID ticketId, ChangeTicketStateDTO dto, UUID updaterId);
    void delete(UUID id);
}
