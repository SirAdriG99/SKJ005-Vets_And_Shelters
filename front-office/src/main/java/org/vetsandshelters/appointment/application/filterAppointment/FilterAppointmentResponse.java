package org.vetsandshelters.appointment.application.filterAppointment;

import org.vetsandshelters.appointment.domain.Appointment;

public class FilterAppointmentResponse {
    private Appointment[] appointments;
    private int totalElements;

    public Appointment[] getAppointments() {
        return appointments;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public FilterAppointmentResponse(Appointment[] appointments, int totalElements) {
        this.appointments = appointments;
        this.totalElements = totalElements;
    }
}
