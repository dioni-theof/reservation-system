package com.dtheof.reservation_system.dto.TicketsDtos;

import com.dtheof.reservation_system.dto.ReservationDtos.ReservationDto;
import com.dtheof.reservation_system.entities.Reservation;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TicketDto(
        String code,
        String eventName,
        BigDecimal price,
        Instant createdOn,
        Instant lastUpdatedOn,
        int availableSeats,
        Set<ReservationDto> infoReservations)
{
}

