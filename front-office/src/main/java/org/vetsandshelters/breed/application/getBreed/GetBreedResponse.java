package org.vetsandshelters.breed.application.getBreed;

import org.vetsandshelters.breed.domain.Breed;

public class GetBreedResponse {
    private Breed breed;

    public GetBreedResponse(Breed breed) {
        this.breed = breed;
    }

    public Breed getBreed() {
        return breed;
    }
}
