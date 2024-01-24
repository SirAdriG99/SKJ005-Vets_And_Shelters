package org.vetsandshelters.breed.domain;

import org.vetsandshelters.shared.DomainUtils.Criteria.Criteria;

public class BreedCriteria extends Criteria {

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
