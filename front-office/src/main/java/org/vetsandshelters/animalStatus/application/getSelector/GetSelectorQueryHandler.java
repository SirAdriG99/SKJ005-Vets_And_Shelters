package org.vetsandshelters.animalStatus.application.getSelector;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.vetsandshelters.animalStatus.domain.AnimalStatusCollection;
import org.vetsandshelters.animalStatus.domain.AnimalStatusRepository;

@ApplicationScoped
public class GetSelectorQueryHandler {
    @Inject
    private AnimalStatusRepository repository;

    public GetSelectorResponse handle() {
        AnimalStatusCollection collection = this.repository.getAll();

        return new GetSelectorResponse(collection.getCollection());

    }
}
