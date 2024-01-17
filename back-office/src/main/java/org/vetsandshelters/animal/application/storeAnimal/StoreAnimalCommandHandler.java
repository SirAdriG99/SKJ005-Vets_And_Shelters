package org.vetsandshelters.animal.application.storeAnimal;

import org.vetsandshelters.animal.domain.Animal;
import org.vetsandshelters.animal.domain.AnimalRepository;
import org.vetsandshelters.animal.domain.Animal_AnimalStatus;
import org.vetsandshelters.animal.domain.Animal_Breed;
import org.vetsandshelters.animal.domain.Animal_ProcedenceType;
import org.vetsandshelters.animal.domain.Animal_Sex;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class StoreAnimalCommandHandler {

    @Inject
    private AnimalRepository repository;

    public StoreAnimalResponse handle(StoreAnimalCommand query) {

        // Here we could do some validations

        Animal animal = new Animal(
                null,
                query.getName(),
                query.getColor(),
                new Animal_Sex(query.getSexId()),
                new Animal_Breed(query.getBreedId()),
                new Animal_ProcedenceType(query.getProcedenceTypeId()),
                new Animal_AnimalStatus(query.getAnimalStatusId()));

        int result = this.repository.store(animal);

        return new StoreAnimalResponse(result);
    }
}
