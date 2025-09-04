package com.dtheof.reservation_system.Mappers;

import com.dtheof.reservation_system.dto.TicketsDtos.TicketDto;
import com.dtheof.reservation_system.dto.TicketsDtos.TicketInfoDto;
import com.dtheof.reservation_system.entities.Ticket;

public class TicketMapper {
    public static Ticket mappingTicketDtoToTicket(TicketDto ticketDto){
        return new Ticket(
                ticketDto.code(),
                ticketDto.eventName(),
                ticketDto.price(),
                ticketDto.createdOn(),
                ticketDto.lastUpdatedOn(),
                ticketDto.availableSeats(),
               null);
    }
    public static TicketDto mappingTicketToTicketDto(Ticket ticket){
        return new TicketDto(ticket.getCode(),
                ticket.getEventName(),
                ticket.getPrice(),
                ticket.getCreatedOn(),
                ticket.getLastUpdatedOn(),
                ticket.getAvailableSeats(),
                ReservationMapper.mappingReservationToReservationDto(ticket.getReservations()));
    }
    public static TicketInfoDto mappingTicketToTicketInfoDto(Ticket ticket){
        return new TicketInfoDto(ticket.getCode(),
                ticket.getEventName(),
                ticket.getPrice(),
                ticket.getCreatedOn(),
                ticket.getLastUpdatedOn(),
                ticket.getAvailableSeats());
    }


}
