package com.dtheof.reservation_system.dto.TicketsDtos;


import java.util.List;

public record AllTicketDto(List<TicketInfoDto> tickets,
                           boolean isLast,
                           long totalPages,
                           long totalElement) {
}
