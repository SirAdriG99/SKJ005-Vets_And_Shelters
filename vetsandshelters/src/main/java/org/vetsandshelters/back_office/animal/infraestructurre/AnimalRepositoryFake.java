package org.vetsandshelters.back_office.animal.infraestructurre;

import org.vetsandshelters.back_office.animal.domain.Animal;
import org.vetsandshelters.back_office.animal.domain.AnimalCollection;
import org.vetsandshelters.back_office.animal.domain.AnimalCriteria;
import org.vetsandshelters.back_office.animal.domain.AnimalRepository;
import org.vetsandshelters.back_office.animal.domain.Race;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Fake Repository to start testing things without a database
 */
@ApplicationScoped
@Priority(100)
public class AnimalRepositoryFake implements AnimalRepository {

    @Override
    public Animal getById(Integer id) {
        return new Animal(1, "Pepito", "Pepito es un perro muy bueno", null);
    }

    @Override
    public AnimalCollection getBy(AnimalCriteria criteria) {
        // TODO Auto-generated method stub
        AnimalCollection toReturn = new AnimalCollection(new Animal[] {
                new Animal(1, "Pepito", "Algo", new Race()),
                new Animal(2, "Menguito", "Algo 2", new Race()),
                new Animal(3, "Juanito", "Algo 3", new Race()),
        });

        return toReturn;
    }

    @Override
    public int add(Animal animal) {
        return 1;
    }

    @Override
    public int update(Animal animal) {
        return 1;
    }

}
