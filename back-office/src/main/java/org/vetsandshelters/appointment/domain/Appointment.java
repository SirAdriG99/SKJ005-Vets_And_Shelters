package org.vetsandshelters.appointment.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;

import java.util.Date;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_id_seq")
    private int id;
//    TODO.todo: We could recover all the object, but right now we will use it as a bridge to know what animal and customer has to recover the front
    @Column(name = "customer_id")
    private int customerId;
//    TODO.todo: We could recover all the object, but right now we will use it as a bridge to know what animal and customer has to recover the front
    @Column(name = "animal_id")
    private int animalId;
    @Column(name = "appointment_date")
    private Date appointmentDate;

    public Appointment(int id, int customerId, int animalId, Date appointmentDate) {
        this.id = id;
        this.customerId = customerId;
        this.animalId = animalId;
        this.appointmentDate = appointmentDate;
    }

    public Appointment() {
        super();
    }

    @Transient
    public static final Appointment NOT_FOUND = new Appointment(-1, -1, -1, null);

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getAnimalId() {
        return animalId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id='" + id + '\'' +
                ", customer_id='" + customerId + '\'' +
                ", animal_id='" + animalId + '\'' +
                ", appointment_date='" + appointmentDate + '\'' +
                '}';
    }
}
