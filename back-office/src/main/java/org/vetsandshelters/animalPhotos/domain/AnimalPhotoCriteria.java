package org.vetsandshelters.animalPhotos.domain;

import org.vetsandshelters.shared.DomainUtils.Criteria.Criteria;
import org.vetsandshelters.shared.DomainUtils.Criteria.FilterCollection;
import org.vetsandshelters.shared.DomainUtils.Pagination.Pagination;

public class AnimalPhotoCriteria extends Criteria {
    public AnimalPhotoCriteria(FilterCollection filters, Pagination pagination) {
        super(filters, pagination);
    }

    public String[] getAllowedFields() {
        return new String[] {
            "id",
            "animalId"
        };
    }
}
