package com.dtheof.reservation_system.dto.CustomersDtos;

import com.dtheof.reservation_system.entities.Reservation;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CustomerDto(String name,
                          @NotBlank
                          String email,
                          String phone,
                          Instant createdOn,
                          Instant lastUpdatedOn,
                          Set<Reservation> infoTicket) {


}
