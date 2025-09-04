package com.dtheof.reservation_system.entities;

import java.io.Serializable;

public class ReservationId implements Serializable {
    private Long idCustomer;
    private Long idTicket;

    public ReservationId() {}

    public ReservationId(Long idCustomer, Long idTicket) {
        this.idCustomer = idCustomer;
        this.idTicket = idTicket;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Long getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Long idTicket) {
        this.idTicket = idTicket;
    }
}
