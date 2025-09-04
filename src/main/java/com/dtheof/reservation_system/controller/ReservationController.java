package com.dtheof.reservation_system.controller;

import com.dtheof.reservation_system.dto.ReservationDtos.AllReservationsDto;
import com.dtheof.reservation_system.dto.ReservationDtos.CreateResrervationDto;
import com.dtheof.reservation_system.dto.ReservationDtos.ReservationDto;
import com.dtheof.reservation_system.dto.ReservationDtos.UpdateReservationDto;
import com.dtheof.reservation_system.dto.SuccessDtos.SuccessResponse;
import com.dtheof.reservation_system.service.ReservationService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/api/v1/reservation")
public class ReservationController {
    private final ReservationService reservationService;


    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping(value = "/{email}/{code}/create-reservation", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ReservationDto> createReservation(@PathVariable String email,
                                                            @PathVariable String code,
                                                            @RequestBody CreateResrervationDto createResrervationDto) {
        ReservationDto reservationDto = reservationService.createReservation(email, code, createResrervationDto);


        return ResponseEntity.created(URI.create("/api/v1/reservation/" + email + "/all-reservation")).body(reservationDto);
    }

    @PutMapping(value = "/{email}/{code}/update-reservation", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable String email,
                                                            @PathVariable String code,
                                                            @RequestBody UpdateReservationDto updateReservationDto) {
        ReservationDto updatedReservationDto = reservationService.updateReservation(email, code, updateReservationDto);
        return ResponseEntity.ok(updatedReservationDto);
    }

    @GetMapping(value = "/{email}/all-reservation", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AllReservationsDto> getReservationByEmail(@PathVariable String email,
                                                                    @RequestParam(name = "size", defaultValue = "5") int size,
                                                                    @RequestParam(name = "page", defaultValue = "0") int page) {
        AllReservationsDto reservationsDto = reservationService.getReservationByEmail(email, PageRequest.of(page, size));
        return ResponseEntity.ok(reservationsDto);

    }

    @GetMapping(value = "/{code}/all-reservation-byCode", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AllReservationsDto> getReservationByCode(@PathVariable String code,
                                                                   @RequestParam(name = "size", defaultValue = "5") int size,
                                                                   @RequestParam(name = "page", defaultValue = "0") int page) {
        AllReservationsDto reservationsDto = reservationService.getReservationByCode(code, PageRequest.of(page, size));
        return ResponseEntity.ok(reservationsDto);

    }

    @DeleteMapping(value = "/{email}/{code}/delete-reservation", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> deleteReservationByEmailAndCode(@PathVariable String email,
                                                                           @PathVariable String code) {
        SuccessResponse deletedReservation = reservationService.deleteReservationByEmailAndCode(email, code);
        return ResponseEntity.ok(deletedReservation);
    }

    @PutMapping(value = "/{email}/{code}/update-status", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ReservationDto> updateStatusReservation(@PathVariable String email,
                                                                  @PathVariable String code,
                                                                  @RequestBody UpdateReservationDto updateStatusDto) {
        ReservationDto reservationDto = reservationService.updateReservation(email, code, updateStatusDto);
        return ResponseEntity.ok(reservationDto);
    }
}
