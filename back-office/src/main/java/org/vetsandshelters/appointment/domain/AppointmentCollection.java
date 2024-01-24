package org.vetsandshelters.appointment.domain;

import org.vetsandshelters.shared.DomainUtils.Collection;

public class AppointmentCollection extends Collection<Appointment>{
    public AppointmentCollection(Appointment[] collection) {
        super(collection);
    }

    public String[] getAllowedFields() {
        return new String[] {
                "id",
                "customerId",
                "animalId",
                "appointmentDate"
        };
    }
}
