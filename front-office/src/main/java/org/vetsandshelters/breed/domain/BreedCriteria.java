package org.vetsandshelters.breed.domain;

import org.vetsandshelters.shared.DomainUtils.Criteria.Criteria;
import org.vetsandshelters.shared.DomainUtils.Criteria.FilterCollection;
import org.vetsandshelters.shared.DomainUtils.Pagination.Pagination;

public class BreedCriteria extends Criteria {
    public BreedCriteria(FilterCollection filters, Pagination pagination) {
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
                "exotic",
                "dangerous",
                "activityNeed",
                "spaceNeed",
                "alimenationNeed",
                "timeDedicationNeed"
        };
    }
}
