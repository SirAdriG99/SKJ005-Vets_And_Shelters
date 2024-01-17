package org.vetsandshelters.animal.infraestructure;

import org.vetsandshelters.animal.domain.Animal;
import org.vetsandshelters.animal.domain.AnimalCollection;
import org.vetsandshelters.animal.domain.AnimalCriteria;
import org.vetsandshelters.animal.domain.AnimalRepository;
import org.vetsandshelters.animal.domain.Breed;
import org.vetsandshelters.animal.domain.ProcedenceType;
import org.vetsandshelters.animal.domain.Sex;

import jakarta.annotation.Priority;
// import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
// import jakarta.enterprise.inject.Alternative;

/**
 * Fake Repository to start testing things without a database
 */
// @Priority(10)
// @ApplicationScoped
public class AnimalRepositoryFake implements AnimalRepository {

    @Override
    public Animal getById(Integer id) {
        return new Animal(
                1,
                "Pepito",
                "Black",
                new Sex(0, "Male"),
                new Breed(0, "Pomerania"),
                new ProcedenceType(0, "Stray"));
    }

    @Override
    public AnimalCollection getBy(AnimalCriteria criteria) {
        // TODO Auto-generated method stub
        AnimalCollection toReturn = new AnimalCollection(new Animal[] {
                new Animal(
                        1,
                        "Pepito",
                        "Black",
                        new Sex(0, "Male"),
                        new Breed(0, "Pomerania"),
                        new ProcedenceType(0, "Stray")),
                new Animal(
                        2,
                        "Menito",
                        "Black",
                        new Sex(0, "Male"),
                        new Breed(0, "Pomerania"),
                        new ProcedenceType(0, "Stray")),
                new Animal(
                        3,
                        "Juanito",
                        "Black",
                        new Sex(0, "Male"),
                        new Breed(0, "Pomerania"),
                        new ProcedenceType(0, "Stray")),
        });

        return toReturn;
    }

    @Override
    public int store(Animal animal) {
        return 1;
    }

    @Override
    public int update(Animal animal) {
        return 1;
    }

}
