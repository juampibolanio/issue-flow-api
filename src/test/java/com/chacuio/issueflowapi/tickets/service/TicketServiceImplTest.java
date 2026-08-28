package com.chacuio.issueflowapi.tickets.service;

import com.chacuio.issueflowapi.tickets.dto.AssignTicketRequestDTO;
import com.chacuio.issueflowapi.tickets.exception.TicketAlreadyClosedException;
import com.chacuio.issueflowapi.tickets.model.State;
import com.chacuio.issueflowapi.tickets.model.Ticket;
import com.chacuio.issueflowapi.tickets.repository.TicketRepository;
import com.chacuio.issueflowapi.users.model.User;
import com.chacuio.issueflowapi.users.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {
    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;

    @Test
    void assign_WhenTicketIsClosed_ShouldThrowTicketAlreadyClosed() {
        // arrange
        UUID ticketId = UUID.randomUUID();
        UUID assignedId = UUID.randomUUID();
        UUID reporterId = UUID.randomUUID();
        AssignTicketRequestDTO dto = new AssignTicketRequestDTO(assignedId);

        User reporterUser = User.builder()
                .id(reporterId)
                .name("Marcus Johnson")
                .email("marcusj@gmail.com")
                .build();

        Ticket closedTicket = Ticket.builder()
                .id(ticketId)
                .title("Resolve bug to API")
                .state(State.CLOSED)
                .reporter(reporterUser)
                .build();

        BDDMockito.given(ticketRepository.findById(ticketId)).willReturn(Optional.of(closedTicket));

        // act & assert
        assertThrows(TicketAlreadyClosedException.class, () -> {
            ticketService.assign(ticketId, dto, reporterId);
        });

        BDDMockito.verify(ticketRepository, BDDMockito.never()).save(BDDMockito.any());
    }
}
