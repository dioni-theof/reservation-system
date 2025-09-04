package com.dtheof.reservation_system.Mappers;

import com.dtheof.reservation_system.dto.ReservationDtos.AllReservationsDto;
import com.dtheof.reservation_system.dto.ReservationDtos.ReservationDto;
import com.dtheof.reservation_system.entities.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.HashSet;
import java.util.Set;

public class ReservationMapper {
    public static ReservationDto mappingReservationToReservationDto(Reservation reservation) {
        return new ReservationDto(reservation.getTicket().getCode(),
                reservation.getCustomer().getEmail(),
                reservation.getSeatsReserved(),
                reservation.getStatus(),
                reservation.getCreatedOn(),
                reservation.getLastUpdatedOn());
    }

    public static Set<ReservationDto> mappingReservationToReservationDto(Set<Reservation> reservations) {
        Set<ReservationDto> reservationDtoSet = new HashSet<>();

        for (Reservation reservation : reservations) {
            reservationDtoSet.add(mappingReservationToReservationDto(reservation));
        }

        return reservationDtoSet;
    }
  public static AllReservationsDto mappingReservationToAllReservationDto(Set<Reservation> reservations, Page<Reservation> result ){
     Set <ReservationDto> reservationDtos = mappingReservationToReservationDto(reservations);

     return new AllReservationsDto(reservationDtos, result.isLast(), result.getTotalPages(), result.getTotalElements());
  }
}
