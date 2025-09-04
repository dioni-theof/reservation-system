package com.dtheof.reservation_system.service;

import com.dtheof.reservation_system.Mappers.ReservationMapper;
import com.dtheof.reservation_system.dto.ReservationDtos.AllReservationsDto;
import com.dtheof.reservation_system.dto.ReservationDtos.CreateResrervationDto;
import com.dtheof.reservation_system.dto.ReservationDtos.ReservationDto;
import com.dtheof.reservation_system.dto.ReservationDtos.UpdateReservationDto;
import com.dtheof.reservation_system.dto.SuccessDtos.SuccessResponse;
import com.dtheof.reservation_system.entities.Customer;
import com.dtheof.reservation_system.entities.Reservation;
import com.dtheof.reservation_system.entities.ReservationId;
import com.dtheof.reservation_system.entities.Ticket;
import com.dtheof.reservation_system.entities.enums.ReservationStatusEnum;
import com.dtheof.reservation_system.entities.enums.SeatNumberChangeEnum;
import com.dtheof.reservation_system.repo.CustomerRepository;
import com.dtheof.reservation_system.repo.ReservationRepository;
import com.dtheof.reservation_system.repo.TicketRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;

    public ReservationService(ReservationRepository reservationRepository, CustomerRepository customerRepository, TicketRepository ticketRepository) {
        this.reservationRepository = reservationRepository;
        this.customerRepository = customerRepository;
        this.ticketRepository = ticketRepository;
    }

    public ReservationDto createReservation(String email, String code, CreateResrervationDto createResrervationDto) {
        Customer customer = customerRepository.findCustomerByEmail(email).orElseThrow();
        Ticket ticket = ticketRepository.findTicketByCode(code).orElseThrow();
        Reservation reservation = new Reservation();
        ReservationId reservationId = new ReservationId();
        reservationId.setIdCustomer(customer.getId_customer());
        reservationId.setIdTicket(ticket.getId_ticket());
//        TODO: ANOTHER CHECK BETTER SOLUTION
        boolean isExist = reservationRepository.existsById(reservationId);
        if (isExist) {
            throw new RuntimeException("Exist");
        }

        reservation.setId(reservationId);
        reservation.setTicket(ticket);
        reservation.setCustomer(customer);
        if (ticket.getAvailableSeats() < createResrervationDto.seats_reserved()) {
            reservation.setSeatsReserved(createResrervationDto.seats_reserved());
//            TODO: USE CUSTOM EXCEPTION WHEN I FIXED
            throw new RuntimeException();
        }

        reservation.setSeatsReserved(createResrervationDto.seats_reserved());
        reservation.setCreatedOn(Instant.now());
        reservation.setLastUpdatedOn(Instant.now());
        Reservation createdReservation = reservationRepository.save(reservation);

        return ReservationMapper.mappingReservationToReservationDto(createdReservation);
    }

    public ReservationDto updateReservation(String email, String code, UpdateReservationDto updateReservationDto) {
        Customer customer = customerRepository.findCustomerByEmail(email).orElseThrow();
        Ticket ticket = ticketRepository.findTicketByCode(code).orElseThrow();
        ReservationId reservationId = new ReservationId(customer.getId_customer(), ticket.getId_ticket());
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow();
        if (reservation.getStatus() == ReservationStatusEnum.CANCELED) {
            throw new RuntimeException("It's already DELETED");
        }
        SeatNumberChangeEnum action = updateReservationDto.action();

        if (SeatNumberChangeEnum.INCREASE == action) {
            if (ticket.getAvailableSeats() - updateReservationDto.seatsNumber() > 0) {
                int newSeats = reservation.getSeatsReserved() + updateReservationDto.seatsNumber();
                reservation.setSeatsReserved(newSeats);


            }

        } else if (SeatNumberChangeEnum.DECREASE == action) {
            if (reservation.getSeatsReserved() > updateReservationDto.seatsNumber()) {
                int newSeats = reservation.getSeatsReserved() - updateReservationDto.seatsNumber();
                reservation.setSeatsReserved(newSeats);

            }

        }
        if (updateReservationDto.statusEnum() != null) {
            reservation.setStatus(updateReservationDto.statusEnum());
            if (reservation.getStatus() == ReservationStatusEnum.CANCELED) {
                ticket.setAvailableSeats(ticket.getAvailableSeats() + reservation.getSeatsReserved());
                ticketRepository.save(ticket);
            }
        }
        reservationRepository.save(reservation);

        return ReservationMapper.mappingReservationToReservationDto(reservation);
    }

    public AllReservationsDto getReservationByEmail(String email, PageRequest pageRequest) {
        Page<Reservation> result = reservationRepository.findReservationByCustomerEmail(email, pageRequest);
        Customer customer = customerRepository.findCustomerByEmail(email).orElseThrow();
        Set<Reservation> reservationSet = customer.getReservations();

        return ReservationMapper.mappingReservationToAllReservationDto(reservationSet, result);
    }

    public AllReservationsDto getReservationByCode(String code, PageRequest pageRequest) {
        Page<Reservation> result = reservationRepository.findReservationByTicketCode(code, pageRequest);
        Ticket ticket = ticketRepository.findTicketByCode(code).orElseThrow();
        Set<Reservation> reservationSet = ticket.getReservations();

        return ReservationMapper.mappingReservationToAllReservationDto(reservationSet, result);
    }


    public SuccessResponse deleteReservationByEmailAndCode(String email, String code) {
        Customer customer = customerRepository.findCustomerByEmail(email).orElseThrow();
        Ticket ticket = ticketRepository.findTicketByCode(code).orElseThrow();
        Reservation reservation = reservationRepository.findReservationByCustomerEmailAndTicket_Code(customer.getEmail(), ticket.getCode()).orElseThrow();
        reservationRepository.delete(reservation);

        return new SuccessResponse(
                "the deleted reservation: USER EMAIL-> "
                        + reservation.getCustomer().getEmail()
                        + " EVENT CODE-> "
                        + reservation.getTicket().getCode());
    }
}
