package com.dtheof.reservation_system.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Set;


@Entity
@Table(
        name="CUSTOMER",
        uniqueConstraints =
        @UniqueConstraint(name ="email_unique", columnNames = {"email"})
)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_customer;
    @Column(nullable = false)
    @NotBlank
    private String name;
    @Column(unique = true)
    @NotBlank
    private String email;

    private String phone;

    @CreationTimestamp(source = SourceType.DB)
    private Instant createdOn;
    @UpdateTimestamp(source = SourceType.DB)
    private Instant lastUpdatedOn;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "customer")
    private Set<Reservation> reservations;


    public Customer() {
    }

    public Customer(String name, String email,String phone, Instant createdOn, Instant lastUpdatedOn) {
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.createdOn=createdOn;
        this.lastUpdatedOn=lastUpdatedOn;
    }

    public Long getId_customer() {
        return id_customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}
