package org.vetsandshelters.animal.infraestructure;

import java.util.List;

import org.vetsandshelters.animal.domain.Animal;
import org.vetsandshelters.animal.domain.AnimalCollection;
import org.vetsandshelters.animal.domain.AnimalCriteria;
import org.vetsandshelters.animal.domain.AnimalRepository;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Priority(5)
@ApplicationScoped
public class AnimalRepositoryPostgreSQL implements AnimalRepository {
    @Inject
    EntityManager em;
    // @Inject
    // EntityManagerFactory emf;

    @Override
    public Animal getById(Integer id) {
        // // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getById'");
        Animal animal = em.find(Animal.class, id);
        if (animal == null) {
            throw new IllegalStateException("Animal not found");
        }
        return animal;
    }

    @Override
    public AnimalCollection getBy(AnimalCriteria criteria) {

        List<Animal> animalList = em.createQuery("FROM Animal", Animal.class).getResultList();

        return new AnimalCollection(animalList.toArray(new Animal[animalList.size()]));
    }

    @Override
    @Transactional
    public int store(Animal animal) {
        // // TODO Auto-generated method stub
        // System.out.println(animal.getBreed().getId());
        // Breed breed = em.find(Breed.class, animal.getBreed().getId());
        // if (breed == null) {
        // throw new IllegalStateException("Breed not found");
        // }
        // Sex sex = em.find(Sex.class, animal.getSex().getId());
        // if (sex == null) {
        // throw new IllegalStateException("Sex not found");
        // }
        // ProcedenceType procedenceType = em.find(ProcedenceType.class,
        // animal.getProcedenceType().getId());
        // if (procedenceType == null) {
        // throw new IllegalStateException("Origin type not found");
        // }
        // animal = new Animal(
        // null,
        // animal.getName(),
        // animal.getColor(),
        // sex,
        // breed,
        // procedenceType);

        // throw new UnsupportedOperationException("Unimplemented method 'store'");
        em.persist(animal);
        return animal.getId();
    }

    @Override
    public int update(Animal animal) {
        em.merge(animal);
        return animal.getId();
    }

}
