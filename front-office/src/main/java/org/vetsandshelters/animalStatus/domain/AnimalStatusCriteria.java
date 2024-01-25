package org.vetsandshelters.animalStatus.domain;

import org.vetsandshelters.shared.DomainUtils.Criteria.Criteria;
import org.vetsandshelters.shared.DomainUtils.Criteria.FilterCollection;
import org.vetsandshelters.shared.DomainUtils.Pagination.Pagination;

public class AnimalStatusCriteria extends Criteria {

    public AnimalStatusCriteria(FilterCollection filters, Pagination pagination) {
        super(filters, pagination);
    }

    public String[] getAllowedFields() {
        return new String[] {
                "id",
                "name"
        };
    }

}
