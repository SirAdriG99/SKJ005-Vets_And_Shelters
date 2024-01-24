package org.vetsandshelters.appointment.application.getAppointment;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.vetsandshelters.appointment.domain.Appointment;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GetAppointmentResponse {
    private Appointment appointment;

    public GetAppointmentResponse(Appointment animal) {
        this.appointment = animal;
    }

    public Appointment getAppointment() {
        return appointment;
    }
}
