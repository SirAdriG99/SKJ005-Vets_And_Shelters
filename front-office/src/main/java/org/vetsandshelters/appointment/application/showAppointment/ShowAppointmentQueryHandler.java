package org.vetsandshelters.front_office.customer.application.showCustomer;

import jakarta.inject.Inject;
import org.vetsandshelters.front_office.customer.domain.Customer;
import org.vetsandshelters.front_office.customer.domain.CustomerRepository;

public class ShowAppointmentQueryHandler {

    /**
     * It exists the posibility of using more than 1 repository in the same handler
     */

    private AppointmentRepository repository;

    @Inject
    public ShowAppointmentQueryHandler(AppointmentRepository repository) {
        this.repository = repository;
    }

    public AppointmentRepository handle(ShowCustomerQuery query) {
        int id = query.getId();

        Appointment appointment = this.repository.getById(id);

        return new AppointmentRepository(appointment);
    }
}
