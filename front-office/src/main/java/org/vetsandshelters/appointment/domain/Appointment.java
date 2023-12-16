package org.vetsandshelters.front_office.customer.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.vetsandshelters.front_office.customer.infraestructurre.Session;

import java.time.LocalDate;

/*
 * In the domain we should only have the data that is relevant for the business logic and the UI.
 * The only methods that should be here are the getters, the constructor and the methods that
 * implement the business logic (p. e. calculate the price of a product with a discount if you receive the full price and the discount as parameters).
 * The setters should only be used if the class is to big and there are some specific use cases that only modify a few fields (p.e. a use case that only modifies 
 * one attribute from a class with 20 attributes).
 */

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Appointment {
    private int customer_id;
    private LocalDateTime appointment_date;
    private int animal_id;

    public Appointment(final int customer_id, final LocalDateTime appointment_date, final int animal_id){
        this.customer_id = customer_id;
        this.appointment_date = appointment_date;
        this.animal_id = animal_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public LocalDateTime getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(LocalDateTime appointment_date) {
        this.appointment_date = appointment_date;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }
}

