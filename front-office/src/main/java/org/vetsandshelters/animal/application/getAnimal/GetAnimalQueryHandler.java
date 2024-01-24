package org.vetsandshelters.animal.application.getAnimal;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.vetsandshelters.animal.domain.Animal;
import org.vetsandshelters.animal.domain.AnimalRepository;

@ApplicationScoped
public class GetAnimalQueryHandler {

    /**
     * It exists the possibility of using more than 1 repository in the same handler
     */

    @Inject
    private AnimalRepository repository;

    public GetAnimalResponse handle(GetAnimalQuery query) {
        int id = query.getId();

        Animal animal = this.repository.getById(id);

        return new GetAnimalResponse(animal);
    }
}
