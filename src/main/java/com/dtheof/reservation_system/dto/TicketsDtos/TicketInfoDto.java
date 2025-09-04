package com.dtheof.reservation_system.dto.TicketsDtos;

import java.math.BigDecimal;
import java.time.Instant;

public record TicketInfoDto(String code,
                            String eventName,
                            BigDecimal price,
                            Instant createdOn,
                            Instant lastUpdatedOn,
                            int availableSeats) {
}
