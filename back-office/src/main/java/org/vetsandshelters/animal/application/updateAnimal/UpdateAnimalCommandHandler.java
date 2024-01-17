package org.vetsandshelters.animal.application.updateAnimal;

import org.vetsandshelters.animal.domain.Animal;
import org.vetsandshelters.animal.domain.AnimalRepository;
import org.vetsandshelters.animal.domain.Breed;
import org.vetsandshelters.animal.domain.ProcedenceType;
import org.vetsandshelters.animal.domain.Sex;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UpdateAnimalCommandHandler {

    @Inject
    private AnimalRepository repository;

    public UpdateAnimalResponse handle(UpdateAnimalCommand query) {

        // Here we could do some validations

        Animal animal = new Animal(
                query.getId(),
                query.getName(),
                query.getColor(),
                new Sex(query.getSexId()),
                new Breed(query.getBreedId()),
                new ProcedenceType(query.getProcedenceTypeId()));

        int result = this.repository.store(animal);

        return new UpdateAnimalResponse(result);
    }
}
