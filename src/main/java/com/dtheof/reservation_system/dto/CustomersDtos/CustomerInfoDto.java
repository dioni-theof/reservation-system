package com.dtheof.reservation_system.dto.CustomersDtos;

import com.dtheof.reservation_system.entities.Reservation;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;
import java.util.Set;

public record CustomerInfoDto(String name,
                              @NotBlank
                              String email,
                              String phone,
                              Instant createdOn,
                              Instant lastUpdatedOn) {
}
