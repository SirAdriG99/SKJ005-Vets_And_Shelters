package org.vetsandshelters.breed.application.getBreed;

import jakarta.persistence.criteria.CriteriaBuilder;

public class GetBreedQuery {
    private Integer id;

    public GetBreedQuery(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
