package org.vetsandshelters.appointment.application.filterAppointment;

import java.util.Date;

public class FilterAppointmentQuery {
    private Integer pageNumber;
    private Integer size;
    private String order;
    private String sort;
    private Integer customerId;
    private Integer animalId;
    private Date appointmentDate;

    public FilterAppointmentQuery(Integer pageNumber, Integer size, String order, String sort, Integer customerId, Integer animalId, Date appointmentDate) {
        this.pageNumber = pageNumber;
        this.size = size;
        this.order = order;
        this.sort = sort;
        this.customerId = customerId;
        this.animalId = animalId;
        this.appointmentDate = appointmentDate;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public Integer getSize() {
        return size;
    }

    public String getOrder() {
        return order;
    }

    public String getSort() {
        return sort;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getAnimalId() {
        return animalId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }
}
