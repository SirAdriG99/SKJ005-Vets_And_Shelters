package org.vetsandshelters.procedenceType.infraestructure;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.vetsandshelters.procedenceType.domain.ProcedenceType;
import org.vetsandshelters.procedenceType.domain.ProcedenceTypeCollection;
import org.vetsandshelters.procedenceType.domain.ProcedenceTypeRepository;

import java.util.List;

@Priority(5)
@ApplicationScoped
public class ProcedenceTypeRepositoryPostgreSQL implements ProcedenceTypeRepository {
    @Inject
    EntityManager em;

    @Override
    public ProcedenceTypeCollection getAll() {
        String query = "FROM ProcedenceType";
        List<ProcedenceType> list = em.createQuery(query, ProcedenceType.class).getResultList();
        return new ProcedenceTypeCollection(list.toArray(new ProcedenceType[list.size()]));
    }

}
