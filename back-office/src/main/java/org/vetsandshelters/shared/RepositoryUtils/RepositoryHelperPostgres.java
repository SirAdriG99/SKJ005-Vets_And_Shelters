package org.vetsandshelters.shared.RepositoryUtils;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.vetsandshelters.breed.domain.Breed;
import org.vetsandshelters.shared.DomainUtils.Criteria.Criteria;
import org.vetsandshelters.shared.DomainUtils.Criteria.Filter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO.todo: This class should be used to implement common methods for all
 * repositories.
 * Operations like generating the petitions or parsing the response
 */

@ApplicationScoped
public class RepositoryHelperPostgres {
    public static List<Object> getCollectionFilteredAndPaginated(EntityManager em, Criteria criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object> cq = cb.createQuery(Object.class);
        Root<Object> objectRoot = cq.from(Object.class);

        Map<String, Predicate> predicates = new HashMap<>();
        for (Filter filter : criteria.getFilterCollection().getFilters()) {
            switch (filter.getOperator()) {
                case EQUALS:
                    predicates.put(filter.getField(), cb.equal(objectRoot.get(filter.getField()), filter.getValue()));
                    break;
                case GREATER_THAN:
                    predicates.put(filter.getField(),
                            cb.greaterThan(objectRoot.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case GREATER_THAN_OR_EQUAL_TO:
                    predicates.put(filter.getField(),
                            cb.greaterThanOrEqualTo(objectRoot.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case LESS_THAN:
                    predicates.put(filter.getField(),
                            cb.lessThan(objectRoot.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case LESS_THAN_OR_EQUAL_TO:
                    predicates.put(filter.getField(),
                            cb.lessThanOrEqualTo(objectRoot.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case NOT_EQUALS:
                    predicates.put(filter.getField(), cb.notEqual(objectRoot.get(filter.getField()), filter.getValue()));
                    break;
                case LIKE, CONTAINS:
                    predicates.put(filter.getField(), cb.like(cb.upper(objectRoot.get(filter.getField())),
                            "%" + filter.getValue().toString().toUpperCase() + "%"));
                    break;
                case IN:
                    predicates.put(filter.getField(), objectRoot.get(filter.getField()).in(filter.getValue()));
                    break;
                case NOT_IN:
                    predicates.put(filter.getField(), cb.not(objectRoot.get(filter.getField()).in(filter.getValue())));
                    break;
                default:
                    break;
            }
        }

        cq.where(predicates.values().toArray(new Predicate[0]));

        TypedQuery<Object> query = em.createQuery(cq);
        if (criteria.getPagination() != null) {
            query.setFirstResult((criteria.getPagination().getPageNumber() - 1) * criteria.getPagination().getSize());
            query.setMaxResults(criteria.getPagination().getSize());
        }

        List<Object> objectList = query.getResultList();
        em.clear();
        return objectList;
    }
}
