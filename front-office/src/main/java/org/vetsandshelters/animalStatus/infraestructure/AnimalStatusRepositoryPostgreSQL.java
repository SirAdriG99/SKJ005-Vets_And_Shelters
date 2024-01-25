package org.vetsandshelters.animalStatus.infraestructure;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.vetsandshelters.animalStatus.domain.AnimalStatus;
import org.vetsandshelters.animalStatus.domain.AnimalStatusCollection;
import org.vetsandshelters.animalStatus.domain.AnimalStatusRepository;

import java.util.List;

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
