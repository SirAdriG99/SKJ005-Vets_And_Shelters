package org.vetsandshelters.back_office.animal.application.getAnimal;

import org.vetsandshelters.back_office.animal.domain.Animal;
import org.vetsandshelters.back_office.animal.domain.AnimalRepository;

import jakarta.inject.Inject;

public class GetAnimalQueryHandler {

    /**
     * It exists the posibility of using more than 1 repository in the same handler
     */

    private AnimalRepository repository;

    @Inject
    public GetAnimalQueryHandler(AnimalRepository repository) {
        this.repository = repository;
    }

    public GetAnimalResponse handle(GetAnimalQuery query) {
        int id = query.getId();

        Animal animal = this.repository.getById(id);

        return new GetAnimalResponse(animal);
    }
}
