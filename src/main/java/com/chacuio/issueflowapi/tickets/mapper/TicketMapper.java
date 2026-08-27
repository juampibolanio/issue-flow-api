package com.chacuio.issueflowapi.tickets.mapper;

import com.chacuio.issueflowapi.tickets.dto.TicketDTO;
import com.chacuio.issueflowapi.tickets.dto.TicketRequestDTO;
import com.chacuio.issueflowapi.tickets.model.Ticket;
import com.chacuio.issueflowapi.users.mapper.UserMapper;
import com.chacuio.issueflowapi.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketMapper {
    private final UserMapper userMapper;

    public TicketDTO toDto(Ticket ticket) {
        return new TicketDTO(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getState(),
                ticket.getPriority(),
                ticket.isActive(),
                userMapper.toSummary(ticket.getAssigned()),
                userMapper.toSummary(ticket.getReporter()),
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
