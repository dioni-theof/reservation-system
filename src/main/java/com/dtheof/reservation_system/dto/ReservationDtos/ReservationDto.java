package com.dtheof.reservation_system.dto.ReservationDtos;

import com.dtheof.reservation_system.entities.Customer;
import com.dtheof.reservation_system.entities.Reservation;
import com.dtheof.reservation_system.entities.Ticket;
import com.dtheof.reservation_system.entities.enums.ReservationStatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ReservationDto(String ticketCode,
                             String customerEmail,
                             int seatsReserved,
                             ReservationStatusEnum status,
                             Instant createdOn,
                             Instant lastUpdatedOn
                             ) {
}
