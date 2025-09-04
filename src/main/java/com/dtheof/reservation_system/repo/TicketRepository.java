package com.dtheof.reservation_system.repo;

import com.dtheof.reservation_system.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findTicketByCode(String code);
    void deleteTicketByCode(String code);
}
