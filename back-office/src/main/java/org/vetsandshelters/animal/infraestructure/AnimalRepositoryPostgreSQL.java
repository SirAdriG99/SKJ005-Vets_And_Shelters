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
            return Animal.NOT_FOUND;
        }
        return animal;
    }

    @Override
    public AnimalCollection getBy(AnimalCriteria criteria) {
        String query = "FROM Animal";
        // query += criteria.getFilterCollection().toQueryWhere();
        // TODO: See how can we create dynamic queries)
        List<Animal> animalList = em.createQuery(query, Animal.class).getResultList();

        return new AnimalCollection(animalList.toArray(new Animal[animalList.size()]));
    }

    @Override
    @Transactional
    public int store(Animal animal) {

        em.persist(animal);
        return animal.getId();
    }

    @Override
    @Transactional
    public int update(Animal animal) {

        em.merge(animal);
        return animal.getId();
    }

}
