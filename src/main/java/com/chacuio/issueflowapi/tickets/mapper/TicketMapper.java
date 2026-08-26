package com.chacuio.issueflowapi.tickets.mapper;

import com.chacuio.issueflowapi.tickets.dto.TicketDTO;
import com.chacuio.issueflowapi.tickets.dto.TicketRequestDTO;
import com.chacuio.issueflowapi.tickets.model.Ticket;
import com.chacuio.issueflowapi.users.model.User;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    public TicketDTO toDto(Ticket ticket, User assigned, User reporter) {
        return new TicketDTO(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getState(),
                ticket.getPriority(),
                ticket.isActive(),
                assigned != null ? assigned.getId() : null,
                reporter != null ? reporter.getId() : null,
                ticket.getCreatedAt(),
                ticket.getUpdatedAt()
        );
    }

    public Ticket toEntity(TicketRequestDTO dto, User assigned, User reporter) {
        return Ticket.builder()
                .title(dto.title())
                .description(dto.description())
                .state(dto.state())
                .priority(dto.priority())
                .assigned(assigned)
                .reporter(reporter)
                .build();
    }
}
