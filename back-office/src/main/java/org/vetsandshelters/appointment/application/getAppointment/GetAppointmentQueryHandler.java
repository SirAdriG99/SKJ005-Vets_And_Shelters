package org.vetsandshelters.appointment.application.getAppointment;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.vetsandshelters.animal.domain.Animal;
import org.vetsandshelters.animal.domain.AnimalRepository;
import org.vetsandshelters.appointment.domain.Appointment;
import org.vetsandshelters.appointment.domain.AppointmentRepository;

@ApplicationScoped
public class GetAppointmentQueryHandler {

    /**
     * It exists the possibility of using more than 1 repository in the same handler
     */

    @Inject
    private AppointmentRepository repository;

    public GetAppointmentResponse handle(GetAppointmentQuery query) {
        int id = query.getId();

        Appointment appointment = this.repository.getById(id);

        return new GetAppointmentResponse(appointment);
    }
}
