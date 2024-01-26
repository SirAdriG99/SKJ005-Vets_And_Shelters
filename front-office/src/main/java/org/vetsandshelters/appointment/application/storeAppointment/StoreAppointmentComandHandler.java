package org.vetsandshelters.appointment.application.storeAppointment;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.vetsandshelters.appointment.domain.Appointment;
import org.vetsandshelters.appointment.domain.AppointmentRepository;

import java.time.Instant;
import java.util.Date;

@ApplicationScoped
public class StoreAppointmentComandHandler {
    @Inject
    AppointmentRepository repository;
    public StoreAppointmentResponse handle(StoreAppointmentComand command) {
        Appointment appointment = new Appointment(
                null,
                command.getAnimalId(),
                command.getCustomerId(),
                Date.from(Instant.parse(command.getDate()))
        );
        int id = this.repository.store(appointment);
        return new StoreAppointmentResponse(id);
    }

}
