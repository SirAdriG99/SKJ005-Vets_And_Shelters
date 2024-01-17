package org.vetsandshelters.animal.application.updateAnimal;

import org.vetsandshelters.animal.domain.Animal;
import org.vetsandshelters.animal.domain.AnimalRepository;
import org.vetsandshelters.animal.domain.Breed;
import org.vetsandshelters.animal.domain.ProcedenceType;
import org.vetsandshelters.animal.domain.Sex;

import com.oracle.graal.compiler.enterprise.phases.strings.q;

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
                query.getSexId() != null ? new Sex(query.getSexId()) : oldAnimal.getSex(),
                query.getBreedId() != null ? new Breed(query.getBreedId()) : oldAnimal.getBreed(),
                query.getProcedenceTypeId() != null ? new ProcedenceType(query.getProcedenceTypeId())
                        : oldAnimal.getProcedenceType());

        int result = this.repository.store(animal);

        return new UpdateAnimalResponse(result);
    }
}
