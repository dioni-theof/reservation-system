package com.dtheof.reservation_system.repo;

import com.dtheof.reservation_system.entities.Reservation;
import com.dtheof.reservation_system.entities.ReservationId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ReservationRepository extends JpaRepository<Reservation, ReservationId> {
    Page<Reservation> findReservationByCustomerEmail(String email,
                                                     Pageable pageable);

    Page<Reservation> findReservationByTicketCode(String ticketCode, Pageable pageable);

   Optional<Reservation>  findReservationByCustomerEmailAndTicket_Code(String customerEmail, String ticketCode);


}
