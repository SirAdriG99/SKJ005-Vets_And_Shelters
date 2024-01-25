package org.vetsandshelters.breed.application.getBreed;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.vetsandshelters.breed.domain.Breed;
import org.vetsandshelters.breed.domain.BreedRepository;

@ApplicationScoped
public class GetBreedQueryHandler {
    @Inject
    private BreedRepository repository;

    public GetBreedResponse handle(GetBreedQuery query) {
        int id = query.getId();
        Breed breed = this.repository.getById(id);

        return new GetBreedResponse(breed);
    }
}
