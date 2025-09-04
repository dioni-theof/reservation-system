package com.dtheof.reservation_system.controller;

import com.dtheof.reservation_system.dto.SuccessDtos.SuccessResponse;
import com.dtheof.reservation_system.dto.TicketsDtos.AllTicketDto;
import com.dtheof.reservation_system.dto.TicketsDtos.TicketDto;
import com.dtheof.reservation_system.dto.TicketsDtos.TicketInfoDto;
import com.dtheof.reservation_system.service.TicketService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/api/v1/ticket")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping(value = "/create-ticket", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto) {
        String addTicket = ticketService.createTicket(ticketDto);
        return ResponseEntity.created(URI.create("/api/v1/ticket/" + addTicket + "/get-info")).build();
    }


    @GetMapping(value = "/all-tickets", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AllTicketDto> getAllTicket(@RequestParam(name = "size", defaultValue = "5") int size,
                                                     @RequestParam(name = "page", defaultValue = "0") int page) {
        AllTicketDto results = ticketService.findTickets(PageRequest.of(page, size));
        return ResponseEntity.ok(results);
    }


    @GetMapping(value = "/{code}/get-info", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TicketInfoDto> getInfoTicketByCode(@PathVariable String code) {
        TicketInfoDto ticket = ticketService.findTicketByCode(code);

        return ResponseEntity.ok(ticket);
    }

    @DeleteMapping(value = "/{code}/delete-ticket", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SuccessResponse> deleteTicket(@PathVariable String code) {
        SuccessResponse deletedTicket = ticketService.deletedTicket(code);

        return ResponseEntity.ok(deletedTicket);
    }


    @PutMapping(value = "/{code}/update-ticket", consumes = "application/json", produces = "application/json")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable String code, @RequestBody TicketDto ticketDto) {
        TicketDto updateTicketDto = ticketService.updateTicket(code, ticketDto);

        return ResponseEntity.ok(updateTicketDto);
    }
}