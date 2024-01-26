package org.vetsandshelters.appointment.domain;

public interface AppointmentRepository {
    public Appointment getById(int id);

    public AppointmentCollection getBy(AppointmentCriteria criteria);

    public int store(Appointment appointment);
}
