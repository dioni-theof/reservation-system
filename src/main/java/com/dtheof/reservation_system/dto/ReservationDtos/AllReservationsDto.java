package com.dtheof.reservation_system.dto.ReservationDtos;

import com.dtheof.reservation_system.dto.CustomersDtos.CustomerInfoDto;

import java.util.List;
import java.util.Set;

public record AllReservationsDto(Set<ReservationDto> reservations,
                                 boolean isLast,
                                 long totalPages,
                                 long totalElement) {
}
