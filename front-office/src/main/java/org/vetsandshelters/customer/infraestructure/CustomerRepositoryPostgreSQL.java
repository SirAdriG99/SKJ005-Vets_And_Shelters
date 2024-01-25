package org.vetsandshelters.customer.infraestructure;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.vetsandshelters.customer.domain.Customer;
import org.vetsandshelters.customer.domain.CustomerCollection;
import org.vetsandshelters.customer.domain.CustomerCriteria;
import org.vetsandshelters.customer.domain.CustomerRepository;
import org.vetsandshelters.shared.DomainUtils.Criteria.Filter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Priority(5)
@ApplicationScoped
public class CustomerRepositoryPostgreSQL implements CustomerRepository {
    @Inject
    EntityManager em;
    @Override
    public Customer getById(Integer id) {
        Customer customer = em.find(Customer.class, id);
        if (customer == null) {
            return Customer.NOT_FOUND;
        }
        return customer;
    }

    @Override
    public CustomerCollection getBy(CustomerCriteria criteria) {
        List<Customer> customerList = getCustomerListPaginated(criteria);
        int totalElements = getCustomerListCount(criteria).intValue();

        return new CustomerCollection(customerList.toArray(new Customer[customerList.size()])
                ,totalElements
        );
    }

    @Override
    public int store(Customer customer) {
        em.persist(customer);
        return customer.getId();
    }

    @Override
    public int update(Customer customer) {
        em.merge(customer);
        return customer.getId();
    }

    private List<Customer> getCustomerListPaginated(CustomerCriteria criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> cq = cb.createQuery(Customer.class);
        Root<Customer> root = cq.from(Customer.class);

        Map<String, Predicate> predicates = new HashMap<>();
        for(Filter filter :criteria.getFilterCollection().getFilters()) {
            switch (filter.getOperator()) {
                case EQUALS:
                    predicates.put(filter.getField(),cb.equal(root.get(filter.getField()), filter.getValue()));
                    break;
                case GREATER_THAN:
                    predicates.put(filter.getField(),cb.greaterThan(root.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case GREATER_THAN_OR_EQUAL_TO:
                    predicates.put(filter.getField(),cb.greaterThanOrEqualTo(root.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case LESS_THAN:
                    predicates.put(filter.getField(),cb.lessThan(root.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case LESS_THAN_OR_EQUAL_TO:
                    predicates.put(filter.getField(),cb.lessThanOrEqualTo(root.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case NOT_EQUALS:
                    predicates.put(filter.getField(),cb.notEqual(root.get(filter.getField()), filter.getValue()));
                    break;
                case LIKE:
                    predicates.put(filter.getField(),cb.like(cb.upper(root.get(filter.getField())),"%" + filter.getValue().toString().toUpperCase() + "%"));
                    break;
                case IN:
                    predicates.put(filter.getField(),root.get(filter.getField()).in(filter.getValue()));
                    break;
                case NOT_IN:
                    predicates.put(filter.getField(),cb.not(root.get(filter.getField()).in(filter.getValue())));
                    break;
            }
        }

        cq.where(predicates.values().toArray(new Predicate[0]));

        TypedQuery<Customer> query = em.createQuery(cq);
        if (criteria.getPagination() != null) {
            query.setFirstResult((criteria.getPagination().getPageNumber()-1)*criteria.getPagination().getSize());
            query.setMaxResults(criteria.getPagination().getSize());
        }

        List<Customer> list = query.getResultList();
        em.clear();
        return list;
    }

    private Long getCustomerListCount(CustomerCriteria criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Customer> root = cq.from(Customer.class);

        Map<String,Predicate> predicates = new HashMap<>();
        for(Filter filter :criteria.getFilterCollection().getFilters()) {
            switch (filter.getOperator()) {
                case EQUALS:
                    predicates.put(filter.getField(),cb.equal(root.get(filter.getField()), filter.getValue()));
                    break;
                case GREATER_THAN:
                    predicates.put(filter.getField(),cb.greaterThan(root.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case GREATER_THAN_OR_EQUAL_TO:
                    predicates.put(filter.getField(),cb.greaterThanOrEqualTo(root.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case LESS_THAN:
                    predicates.put(filter.getField(),cb.lessThan(root.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case LESS_THAN_OR_EQUAL_TO:
                    predicates.put(filter.getField(),cb.lessThanOrEqualTo(root.get(filter.getField()), (Comparable) filter.getValue()));
                    break;
                case NOT_EQUALS:
                    predicates.put(filter.getField(),cb.notEqual(root.get(filter.getField()), filter.getValue()));
                    break;
                case LIKE:
                    predicates.put(filter.getField(),cb.like(cb.upper(root.get(filter.getField())),"%" + filter.getValue().toString().toUpperCase() + "%"));
                    break;
                case IN:
                    predicates.put(filter.getField(),root.get(filter.getField()).in(filter.getValue()));
                    break;
                case NOT_IN:
                    predicates.put(filter.getField(),cb.not(root.get(filter.getField()).in(filter.getValue())));
                    break;
            }
        }

        cq.where(predicates.values().toArray(new Predicate[0]));

        cq.select(cb.count(root));

        TypedQuery<Long> query = em.createQuery(cq);

        Long count = query.getSingleResult();
        em.clear();
        return count;
    }
}
