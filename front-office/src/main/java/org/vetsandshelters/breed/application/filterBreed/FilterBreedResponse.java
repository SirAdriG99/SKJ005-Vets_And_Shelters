package org.vetsandshelters.breed.application.filterBreed;

import org.vetsandshelters.breed.domain.Breed;

public class FilterBreedResponse {
    private Breed[] breeds;

    private int totalElements;

    public FilterBreedResponse(Breed[] breeds, int totalElements) {
        this.breeds = breeds;
        this.totalElements = totalElements;
    }

    public Breed[] getBreeds() {
        return breeds;
    }

    public int getTotalElements() {
        return totalElements;
    }
}
