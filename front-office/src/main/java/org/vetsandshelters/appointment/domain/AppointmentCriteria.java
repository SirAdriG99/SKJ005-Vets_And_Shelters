package org.vetsandshelters.front_office.customer.domain;

import org.vetsandshelters.shared.DomainUtils.Criteria.Criteria;
import org.vetsandshelters.shared.DomainUtils.Criteria.FilterCollection;
import org.vetsandshelters.shared.DomainUtils.Pagination.Pagination;

public class AppointmentCriteria extends Criteria {

    public AppointmentCriteria(FilterCollection filters, Pagination pagination) {
        super(filters, pagination);
    }

    /**
     * Returns the allowed fields for filtering
     * 
     * @return
     */
    public String[] getAllowedFields() {
        return new String[] {
                "customer_id",
                "appointment_date",
                "animal_id",
        };
    }
}
