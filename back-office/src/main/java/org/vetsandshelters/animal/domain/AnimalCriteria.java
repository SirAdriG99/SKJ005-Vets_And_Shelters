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
                "sexIdd",
                "breedId",
                "procedenceTypeId",
                "animalStatusId"
        };
    }

}
