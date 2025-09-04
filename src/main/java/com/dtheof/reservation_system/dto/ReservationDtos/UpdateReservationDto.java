package com.dtheof.reservation_system.dto.ReservationDtos;

import com.dtheof.reservation_system.entities.enums.ReservationStatusEnum;
import com.dtheof.reservation_system.entities.enums.SeatNumberChangeEnum;

public record UpdateReservationDto(SeatNumberChangeEnum action,
                                   Integer seatsNumber,
                                   ReservationStatusEnum statusEnum) {
    public UpdateReservationDto(SeatNumberChangeEnum action, int seatsNumber) {
     this(action,seatsNumber,null);
    }

    public UpdateReservationDto( ReservationStatusEnum statusEnum) {
        this(null,null,statusEnum);
    }
}
