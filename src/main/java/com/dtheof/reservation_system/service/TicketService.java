package com.dtheof.reservation_system.service;

import com.dtheof.reservation_system.Mappers.TicketMapper;
import com.dtheof.reservation_system.dto.SuccessDtos.SuccessResponse;
import com.dtheof.reservation_system.dto.TicketsDtos.AllTicketDto;
import com.dtheof.reservation_system.dto.TicketsDtos.TicketDto;
import com.dtheof.reservation_system.dto.TicketsDtos.TicketInfoDto;
import com.dtheof.reservation_system.entities.Ticket;
import com.dtheof.reservation_system.repo.TicketRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public String createTicket(TicketDto ticketDto) {
        Ticket newTicket = TicketMapper.mappingTicketDtoToTicket(ticketDto);

        return ticketRepository.save(newTicket).getCode();
    }

    public AllTicketDto findTickets(PageRequest pageRequest) {
        Page<Ticket> result = ticketRepository.findAll(pageRequest);

        List<Ticket> tickets = result.getContent();
        List<TicketInfoDto> ticketInfoDtos = new ArrayList<>();
        for (Ticket ticket : tickets) {
            TicketInfoDto ticketInfoDto = new TicketInfoDto(ticket.getCode(),
                    ticket.getEventName(),
                    ticket.getPrice(),
                    ticket.getCreatedOn(),
                    ticket.getLastUpdatedOn(),
                    ticket.getAvailableSeats());

            ticketInfoDtos.add(ticketInfoDto);
        }
        return new AllTicketDto(ticketInfoDtos, result.isLast(), result.getTotalPages(), result.getTotalElements());
    }

    public TicketInfoDto findTicketByCode(String code) {
        Ticket ticket = ticketRepository.findTicketByCode(code).orElseThrow();

        return TicketMapper.mappingTicketToTicketInfoDto(ticket);
    }

    public TicketDto updateTicket(String code, TicketDto ticketDto) {
        Ticket updateTicket = ticketRepository.findTicketByCode(code).orElseThrow();
        if (ticketDto.availableSeats() != 0) {
            updateTicket.setEventName(updateTicket.getEventName());
        }
        if (ticketDto.price() != null) {
            updateTicket.setPrice(updateTicket.getPrice());
        }
        if (ticketDto.availableSeats() != 0) {
            updateTicket.setAvailableSeats(updateTicket.getAvailableSeats());
        }
        updateTicket.setLastUpdatedOn(Instant.now());
        updateTicket = ticketRepository.save(updateTicket);

        return TicketMapper.mappingTicketToTicketDto(updateTicket);
    }

    @Transactional
    public SuccessResponse deletedTicket(String code) {
        Ticket deletedTicket = ticketRepository.findTicketByCode(code).orElseThrow();
        ticketRepository.deleteTicketByCode(code);

        return new SuccessResponse("Success deleted " + deletedTicket.getCode());
    }
}
