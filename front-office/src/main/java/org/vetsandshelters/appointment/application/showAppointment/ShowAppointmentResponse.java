package org.vetsandshelters.front_office.customer.application.showCustomer;

import org.vetsandshelters.front_office.customer.domain.Customer;

public class ShowAppointmentResponse {
    public Appointment appointment;

    public ShowAppointmentResponse(Appointment appointment) {
        this.appointment = appointment;
    }

    public Appointment getAppointment() {
        return this.appointment;
    }
}
