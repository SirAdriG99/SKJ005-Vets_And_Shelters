package org.vetsandshelters.animal.domain;

import org.vetsandshelters.shared.DomainUtils.Criteria.Criteria;
import org.vetsandshelters.shared.DomainUtils.Criteria.FilterCollection;
import org.vetsandshelters.shared.DomainUtils.Pagination.Pagination;

public class AnimalCriteria extends Criteria {

    public AnimalCriteria(FilterCollection filters, Pagination pagination) {
        super(filters, pagination);
    }

    /**
     * Returns the allowed fields for filtering
     * 
     * @return
     */
    public String[] getAllowedFields() {
        return new String[] {
                "id",
                "name",
                "color",
                "sex_id",
                "breed_id",
                "procedence_type_id",
                "animal_status_id"
        };
    }

}
