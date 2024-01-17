package org.vetsandshelters.animal.application.updateAnimal;

import org.vetsandshelters.animal.domain.Animal;
import org.vetsandshelters.animal.domain.AnimalRepository;
import org.vetsandshelters.animal.domain.Animal_AnimalStatus;
import org.vetsandshelters.animal.domain.Animal_Breed;
import org.vetsandshelters.animal.domain.Animal_ProcedenceType;
import org.vetsandshelters.animal.domain.Animal_Sex;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UpdateAnimalCommandHandler {

    @Inject
    private AnimalRepository repository;

    public UpdateAnimalResponse handle(UpdateAnimalCommand query) {

        // Here we could do some validations
        Animal oldAnimal = null;
        try {
            oldAnimal = this.repository.getById(query.getId());
        } catch (IllegalStateException e) {
            return new UpdateAnimalResponse(-1);
        }

        Animal animal = new Animal(
                query.getId(),
                query.getName() != null ? query.getName() : oldAnimal.getName(),
                query.getColor() != null ? query.getColor() : oldAnimal.getColor(),
                query.getSexId() != null ? new Animal_Sex(query.getSexId()) : oldAnimal.getSex(),
                query.getBreedId() != null ? new Animal_Breed(query.getBreedId()) : oldAnimal.getBreed(),
                query.getProcedenceTypeId() != null ? new Animal_ProcedenceType(query.getProcedenceTypeId())
                        : oldAnimal.getProcedenceType(),
                query.getAnimalStatusId() != null ? new Animal_AnimalStatus(query.getAnimalStatusId())
                        : oldAnimal.getAnimalStatus());

        int result = this.repository.update(animal);

        return new UpdateAnimalResponse(result);
    }
}
