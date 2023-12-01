package org.vetsandshelters.back_office.animal.application.showAnimal;

import org.vetsandshelters.back_office.animal.domain.Animal;
import org.vetsandshelters.back_office.animal.domain.AnimalRepository;

public class ShowAnimalQueryHandler {
    private AnimalRepository repository;

    public ShowAnimalQueryHandler(AnimalRepository repository) {
        this.repository = repository;
    }

    public ShowAnimalResponse handle(ShowAnimalQuery query) {
        int id = query.getId();

        Animal animal = this.repository.getById(id);

        return new ShowAnimalResponse(animal);
    }
}
