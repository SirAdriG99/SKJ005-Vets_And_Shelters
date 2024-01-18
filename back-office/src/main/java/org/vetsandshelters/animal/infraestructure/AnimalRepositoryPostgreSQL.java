package org.vetsandshelters.animal.infraestructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.vetsandshelters.animal.domain.Animal;
import org.vetsandshelters.animal.domain.AnimalCollection;
import org.vetsandshelters.animal.domain.AnimalCriteria;
import org.vetsandshelters.animal.domain.AnimalRepository;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.vetsandshelters.shared.DomainUtils.Criteria.Filter;

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
        List<Animal> animalList = getAnimalListPaginated(criteria);
        int totalElements = getAnimalListCount(criteria).intValue();

        return new AnimalCollection(animalList.toArray(new Animal[animalList.size()]), totalElements);
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

    private List<Animal> getAnimalListPaginated(AnimalCriteria criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Animal> cq = cb.createQuery(Animal.class);
        Root<Animal> animal = cq.from(Animal.class);

        Map<String, Predicate> predicates = new HashMap<>();
        for (Filter filter : criteria.getFilterCollection().getFilters()) {
            switch (filter.getOperator()) {
                case EQUALS:
                    predicates.put(filter.getField(), cb.equal(animal.get(filter.getField()), filter.getValue()));
                    break;
                case GREATER_THAN:
                    predicates.put(filter.getField(),
                            cb.greaterThan(animal.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case GREATER_THAN_OR_EQUAL_TO:
                    predicates.put(filter.getField(),
                            cb.greaterThanOrEqualTo(animal.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case LESS_THAN:
                    predicates.put(filter.getField(),
                            cb.lessThan(animal.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case LESS_THAN_OR_EQUAL_TO:
                    predicates.put(filter.getField(),
                            cb.lessThanOrEqualTo(animal.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case NOT_EQUALS:
                    predicates.put(filter.getField(), cb.notEqual(animal.get(filter.getField()), filter.getValue()));
                    break;
                case LIKE, CONTAINS:
                    predicates.put(filter.getField(), cb.like(cb.upper(animal.get(filter.getField())),
                            "%" + filter.getValue().toString().toUpperCase() + "%"));
                    break;
                case IN:
                    predicates.put(filter.getField(), animal.get(filter.getField()).in(filter.getValue()));
                    break;
                case NOT_IN:
                    predicates.put(filter.getField(), cb.not(animal.get(filter.getField()).in(filter.getValue())));
                    break;
                default:
                    break;
            }
        }

        cq.where(predicates.values().toArray(new Predicate[0]));

        TypedQuery<Animal> query = em.createQuery(cq);
        if (criteria.getPagination() != null) {
            query.setFirstResult((criteria.getPagination().getPageNumber() - 1) * criteria.getPagination().getSize());
            query.setMaxResults(criteria.getPagination().getSize());
        }

        List<Animal> animalList = query.getResultList();
        em.clear();
        return animalList;
    }

    private Long getAnimalListCount(AnimalCriteria criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Animal> animal = cq.from(Animal.class);

        Map<String, Predicate> predicates = new HashMap<>();
        for (Filter filter : criteria.getFilterCollection().getFilters()) {
            switch (filter.getOperator()) {
                case EQUALS:
                    predicates.put(filter.getField(), cb.equal(animal.get(filter.getField()), filter.getValue()));
                    break;
                case GREATER_THAN:
                    predicates.put(filter.getField(),
                            cb.greaterThan(animal.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case GREATER_THAN_OR_EQUAL_TO:
                    predicates.put(filter.getField(),
                            cb.greaterThanOrEqualTo(animal.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case LESS_THAN:
                    predicates.put(filter.getField(),
                            cb.lessThan(animal.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case LESS_THAN_OR_EQUAL_TO:
                    predicates.put(filter.getField(),
                            cb.lessThanOrEqualTo(animal.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case NOT_EQUALS:
                    predicates.put(filter.getField(), cb.notEqual(animal.get(filter.getField()), filter.getValue()));
                    break;
                case LIKE:
                    predicates.put(filter.getField(), cb.like(cb.upper(animal.get(filter.getField())),
                            "%" + filter.getValue().toString().toUpperCase() + "%"));
                    break;
                case IN:
                    predicates.put(filter.getField(), animal.get(filter.getField()).in(filter.getValue()));
                    break;
                case NOT_IN:
                    predicates.put(filter.getField(), cb.not(animal.get(filter.getField()).in(filter.getValue())));
                    break;
                default:
                    break;
            }
        }

        cq.where(predicates.values().toArray(new Predicate[0]));

        cq.select(cb.count(animal));

        TypedQuery<Long> query = em.createQuery(cq);

        Long count = query.getSingleResult();
        em.clear();
        return count;
    }

}
