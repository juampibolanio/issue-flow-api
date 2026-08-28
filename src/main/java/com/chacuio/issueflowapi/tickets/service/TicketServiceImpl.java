package com.chacuio.issueflowapi.tickets.service;

import com.chacuio.issueflowapi.common.exception.AccessDeniedException;
import com.chacuio.issueflowapi.tickets.dto.AssignTicketRequestDTO;
import com.chacuio.issueflowapi.tickets.dto.ChangeTicketStateDTO;
import com.chacuio.issueflowapi.tickets.dto.TicketDTO;
import com.chacuio.issueflowapi.tickets.dto.TicketRequestDTO;
import com.chacuio.issueflowapi.tickets.exception.TicketAlreadyClosedException;
import com.chacuio.issueflowapi.tickets.exception.TicketNotFoundException;
import com.chacuio.issueflowapi.tickets.mapper.TicketMapper;
import com.chacuio.issueflowapi.tickets.model.State;
import com.chacuio.issueflowapi.tickets.model.Ticket;
import com.chacuio.issueflowapi.tickets.repository.TicketRepository;
import com.chacuio.issueflowapi.users.exception.UserNotFoundException;
import com.chacuio.issueflowapi.users.model.User;
import com.chacuio.issueflowapi.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final TicketMapper mapper;

    @Override
    @Transactional
    public TicketDTO create(TicketRequestDTO dto) {
        User reporter;
        User assigned = null;

        reporter = userRepository.findById(dto.reporterId())
                .orElseThrow(() -> new UserNotFoundException(dto.reporterId()));

        if (dto.assignedId() != null) {
            assigned = userRepository.findById(dto.assignedId())
                    .orElseThrow(() -> new UserNotFoundException(dto.assignedId()));
        }

        Ticket mappedTicket = mapper.toEntity(dto, assigned, reporter);
        Ticket savedTicket = ticketRepository.save(mappedTicket);

        return mapper.toDto(savedTicket);
    }

    @Override
    public List<TicketDTO> findAll() {
        List<Ticket> tickets = ticketRepository.findAll();

        return tickets.stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public TicketDTO findById(UUID id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
        return mapper.toDto(ticket);
    }

    @Override
    @Transactional
    public TicketDTO assign(UUID ticketId, AssignTicketRequestDTO dto, UUID requesterId) {
        // validate if tickets exists
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException(ticketId));

        // validate ticket state
        if (ticket.getState() == State.CLOSED) {
            throw new TicketAlreadyClosedException(ticketId);
        }

        // verify if reporter and assigned users have permission to assign the ticket
        UUID reporterId = ticket.getReporter().getId();
        UUID assignedId = (ticket.getAssigned() != null) ? ticket.getAssigned().getId() : null;

        boolean isReporter = requesterId.equals(reporterId);
        boolean isAssigned = requesterId.equals(assignedId);

        if (!isReporter && !isAssigned) {
            throw new AccessDeniedException("The user does not have permission to assign users to this ticket.");
        }

        // search assignee user
        User assigneeUser = userRepository.findById(dto.assigneeId())
                .orElseThrow(() -> new UserNotFoundException(dto.assigneeId()));

        // assign ticket
        ticket.setAssigned(assigneeUser);
        return mapper.toDto(ticketRepository.save(ticket));
    }

    @Override
    public TicketDTO changeState(UUID ticketId, ChangeTicketStateDTO dto, UUID updaterId) {
        // validate if ticket exists
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException(ticketId));

        // extract ids
        UUID reporterId = ticket.getReporter().getId();
        UUID assignedId = (ticket.getAssigned() != null) ? ticket.getAssigned().getId() : null;

        // validate if updater user has permission to change the ticket state
        boolean isReporter = updaterId.equals(reporterId);
        boolean isAssigned = updaterId.equals(assignedId);

        if (!isAssigned && !isReporter) {
            throw new AccessDeniedException("The updater user does not have permission to change the state of this ticket.");
        }

        // change ticket state
        ticket.setState(dto.state());
        return mapper.toDto(ticketRepository.save(ticket));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));

        ticket.setActive(false);
        ticketRepository.save(ticket);
    }
}
