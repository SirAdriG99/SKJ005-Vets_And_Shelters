package org.vetsandshelters.animalPhotos.infraestructure;

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
import org.vetsandshelters.animalPhotos.domain.AnimalPhoto;
import org.vetsandshelters.animalPhotos.domain.AnimalPhotoCollection;
import org.vetsandshelters.animalPhotos.domain.AnimalPhotoCriteria;
import org.vetsandshelters.animalPhotos.domain.AnimalPhotoRepository;
import org.vetsandshelters.shared.DomainUtils.Criteria.Filter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Priority(5)
@ApplicationScoped
public class AnimalPhotoRepositoryPostgreSQL implements AnimalPhotoRepository {
    @Inject
    EntityManager em;
    @Override
    public AnimalPhoto getById(int id) {
        AnimalPhoto photo = em.find(AnimalPhoto.class, id);
        if (photo == null) {
            return AnimalPhoto.NOT_FOUND;
        }
        return photo;
    }

    @Override
    public AnimalPhotoCollection getBy(AnimalPhotoCriteria criteria) {
        List<AnimalPhoto> animalList = getAnimalPhotoListPaginated(criteria);
        int totalElements = getAnimalPhotoListCount(criteria).intValue();

        return new AnimalPhotoCollection(animalList.toArray(new AnimalPhoto[animalList.size()]), totalElements);
    }

    @Transactional
    @Override
    public int store(AnimalPhoto animalPhoto) {
//        TODO: Se how to handle the images locally or maybe create another service to handle the images
        em.persist(animalPhoto);
        return animalPhoto.getId();
    }

//    @Transactional
//    @Override
//    public boolean storeAll(AnimalPhotoCollection animalPhotoCollection) {
//        // Store multiple photos using entity manager
//
//        return false;
//    }

    private List<AnimalPhoto> getAnimalPhotoListPaginated(AnimalPhotoCriteria criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<AnimalPhoto> cq = cb.createQuery(AnimalPhoto.class);
        Root<AnimalPhoto> animal = cq.from(AnimalPhoto.class);

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

        TypedQuery<AnimalPhoto> query = em.createQuery(cq);
        if (criteria.getPagination() != null) {
            query.setFirstResult((criteria.getPagination().getPageNumber() - 1) * criteria.getPagination().getSize());
            query.setMaxResults(criteria.getPagination().getSize());
        }

        List<AnimalPhoto> animalList = query.getResultList();
        em.clear();
        return animalList;
    }

    private Long getAnimalPhotoListCount(AnimalPhotoCriteria criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<AnimalPhoto> animal = cq.from(AnimalPhoto.class);

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
