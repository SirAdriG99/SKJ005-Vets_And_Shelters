package org.vetsandshelters.sex.infraestructure;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.vetsandshelters.sex.domain.Sex;
import org.vetsandshelters.sex.domain.SexCollection;
import org.vetsandshelters.sex.domain.SexRepository;

import java.util.List;

@Priority(5)
@ApplicationScoped
public class SexRepositoryPostgreSQL implements SexRepository {
    @Inject
    EntityManager em;

    @Override
    public SexCollection getAll() {
        String query = "FROM Sex";
        List<Sex> sexList = em.createQuery(query, Sex.class).getResultList();
        return new SexCollection(sexList.toArray(new Sex[sexList.size()]));
    }

}
