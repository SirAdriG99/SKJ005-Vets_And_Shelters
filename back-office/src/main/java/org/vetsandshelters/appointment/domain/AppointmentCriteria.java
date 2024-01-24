package org.vetsandshelters.appointment.domain;

import org.vetsandshelters.shared.DomainUtils.Criteria.Criteria;
import org.vetsandshelters.shared.DomainUtils.Criteria.FilterCollection;
import org.vetsandshelters.shared.DomainUtils.Pagination.Pagination;

public class AppointmentCriteria extends Criteria{
    public AppointmentCriteria(FilterCollection filters, Pagination pagination) {
        super(filters, pagination);
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
