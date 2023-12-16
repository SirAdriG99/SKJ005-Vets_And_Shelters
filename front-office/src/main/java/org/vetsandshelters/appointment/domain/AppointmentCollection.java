package org.vetsandshelters.front_office.customer.domain;

import org.vetsandshelters.shared.DomainUtils.Collection;

/*
 * The collections represent a group of objects of the same type.
 * They're all extensions of the Collection class.
 * The collection class has all the methods that we're going to need
 */
public class AppointmentCollection extends Collection<Appointment> {
    public AppointmentCollection(Appointment[] appointments) {
        super(appointments);
    }
}
