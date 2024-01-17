package org.vetsandshelters.animalStatus.infraestructure;

import java.util.List;

import org.vetsandshelters.animalStatus.domain.AnimalStatus;
import org.vetsandshelters.animalStatus.domain.AnimalStatusCollection;
import org.vetsandshelters.animalStatus.domain.AnimalStatusRepository;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@Priority(5)
@ApplicationScoped
public class AnimalStatusRepositoryPostgreSQL implements AnimalStatusRepository {
    @Inject
    EntityManager em;

    @Override
    public AnimalStatusCollection getAll() {
        String query = "FROM AnimalStatus";
        List<AnimalStatus> list = em.createQuery(query, AnimalStatus.class).getResultList();
        return new AnimalStatusCollection(list.toArray(new AnimalStatus[list.size()]));
    }

}
