package com.dtheof.reservation_system.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_ticket;
    @Column(unique = true)
    @NotNull
    private String code;
    private String eventName;
    @Column(precision = 10, scale = 2)
    @NotNull
    private BigDecimal price;
    @CreationTimestamp(source = SourceType.DB)
    private Instant createdOn;
    @UpdateTimestamp(source = SourceType.DB)
    private Instant lastUpdatedOn;
    @NotNull
    @Min(0)
    private int availableSeats;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "ticket")
    private Set<Reservation> reservations;

    public Ticket() {
    }

    public Ticket(String code, String eventName, BigDecimal price, Instant createdOn, Instant lastUpdatedOn, int availableSeats, Set<Reservation> reservations) {
        this.code = code;
        this.eventName = eventName;
        this.price = price;
        this.createdOn = createdOn;
        this.lastUpdatedOn = lastUpdatedOn;
        this.availableSeats = availableSeats;
        this.reservations = reservations;
    }

    public Long getId_ticket() {
        return id_ticket;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String event_name) {
        this.eventName = event_name;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Instant getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(Instant lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int available_seats) {
        this.availableSeats = available_seats;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
