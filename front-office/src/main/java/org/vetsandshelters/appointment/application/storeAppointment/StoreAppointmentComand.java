package org.vetsandshelters.appointment.application.storeAppointment;

import java.util.Date;

public class StoreAppointmentComand {
    private int animalId;
    private int customerId;
    private String date;

    public StoreAppointmentComand(int animalId, int customerId, String date) {
        this.animalId = animalId;
        this.customerId = customerId;
        this.date = date;
    }

    public int getAnimalId() {
        return animalId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getDate() {
        return date;
    }
}
