package org.vetsandshelters.front_office.customer.domain;

public interface CustomerRepository {
    public Appointment getById(Integer id);

    public AppointmentCollection getBy(AppointmentCollection criteria);

    public int add(Appointment appointment);

    public int update(Appointment appointment);
}
