package org.vetsandshelters.breed.infraestructure;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.vetsandshelters.animal.domain.Animal;
import org.vetsandshelters.animal.domain.AnimalCollection;
import org.vetsandshelters.animal.domain.AnimalCriteria;
import org.vetsandshelters.breed.domain.Breed;
import org.vetsandshelters.breed.domain.BreedCollection;
import org.vetsandshelters.breed.domain.BreedCriteria;
import org.vetsandshelters.breed.domain.BreedRepository;
import org.vetsandshelters.shared.DomainUtils.Criteria.Filter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Priority(5)
@ApplicationScoped
public class BreedRepositoryPostgreSQL implements BreedRepository {

    @Inject
    EntityManager em;
    @Override
    public Breed getById(int id) {
        Breed breed = em.find(Breed.class, id);
        if (breed == null) {
            return Breed.NOT_FOUND;
        }
        return breed;
    }

    @Override
    public BreedCollection getByCriteria(BreedCriteria criteria) {
        List<Breed> breedList = getBreedListPaginated(criteria);
        int totalElements = getBreedListCount(criteria).intValue();
        return new BreedCollection(breedList.toArray(new Breed[breedList.size()]), totalElements);
    }

    @Override
    @Transactional
    public int store(Breed breed) {
        em.persist(breed);
        return breed.getId();
    }

    @Override
    @Transactional
    public int update(Breed breed) {
        em.merge(breed);
        return breed.getId();
    }

    private List<Breed> getBreedListPaginated(BreedCriteria criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Breed> cq = cb.createQuery(Breed.class);
        Root<Breed> breed = cq.from(Breed.class);

        Map<String, Predicate> predicates = new HashMap<>();
        for (Filter filter : criteria.getFilterCollection().getFilters()) {
            switch (filter.getOperator()) {
                case EQUALS:
                    predicates.put(filter.getField(), cb.equal(breed.get(filter.getField()), filter.getValue()));
                    break;
                case GREATER_THAN:
                    predicates.put(filter.getField(),
                            cb.greaterThan(breed.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case GREATER_THAN_OR_EQUAL_TO:
                    predicates.put(filter.getField(),
                            cb.greaterThanOrEqualTo(breed.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case LESS_THAN:
                    predicates.put(filter.getField(),
                            cb.lessThan(breed.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case LESS_THAN_OR_EQUAL_TO:
                    predicates.put(filter.getField(),
                            cb.lessThanOrEqualTo(breed.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case NOT_EQUALS:
                    predicates.put(filter.getField(), cb.notEqual(breed.get(filter.getField()), filter.getValue()));
                    break;
                case LIKE, CONTAINS:
                    predicates.put(filter.getField(), cb.like(cb.upper(breed.get(filter.getField())),
                            "%" + filter.getValue().toString().toUpperCase() + "%"));
                    break;
                case IN:
                    predicates.put(filter.getField(), breed.get(filter.getField()).in(filter.getValue()));
                    break;
                case NOT_IN:
                    predicates.put(filter.getField(), cb.not(breed.get(filter.getField()).in(filter.getValue())));
                    break;
                default:
                    break;
            }
        }

        cq.where(predicates.values().toArray(new Predicate[0]));

        TypedQuery<Breed> query = em.createQuery(cq);
        if (criteria.getPagination() != null) {
            query.setFirstResult((criteria.getPagination().getPageNumber() - 1) * criteria.getPagination().getSize());
            query.setMaxResults(criteria.getPagination().getSize());
        }

        List<Breed> breedList = query.getResultList();
        em.clear();
        return breedList;
    }

    private Long getBreedListCount(BreedCriteria criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Breed> breed = cq.from(Breed.class);

        Map<String, Predicate> predicates = new HashMap<>();
        for (Filter filter : criteria.getFilterCollection().getFilters()) {
            switch (filter.getOperator()) {
                case EQUALS:
                    predicates.put(filter.getField(), cb.equal(breed.get(filter.getField()), filter.getValue()));
                    break;
                case GREATER_THAN:
                    predicates.put(filter.getField(),
                            cb.greaterThan(breed.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case GREATER_THAN_OR_EQUAL_TO:
                    predicates.put(filter.getField(),
                            cb.greaterThanOrEqualTo(breed.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case LESS_THAN:
                    predicates.put(filter.getField(),
                            cb.lessThan(breed.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case LESS_THAN_OR_EQUAL_TO:
                    predicates.put(filter.getField(),
                            cb.lessThanOrEqualTo(breed.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case NOT_EQUALS:
                    predicates.put(filter.getField(), cb.notEqual(breed.get(filter.getField()), filter.getValue()));
                    break;
                case LIKE:
                    predicates.put(filter.getField(), cb.like(cb.upper(breed.get(filter.getField())),
                            "%" + filter.getValue().toString().toUpperCase() + "%"));
                    break;
                case IN:
                    predicates.put(filter.getField(), breed.get(filter.getField()).in(filter.getValue()));
                    break;
                case NOT_IN:
                    predicates.put(filter.getField(), cb.not(breed.get(filter.getField()).in(filter.getValue())));
                    break;
                default:
                    break;
            }
        }

        cq.where(predicates.values().toArray(new Predicate[0]));

        cq.select(cb.count(breed));

        TypedQuery<Long> query = em.createQuery(cq);

        Long count = query.getSingleResult();
        em.clear();
        return count;
    }
}
