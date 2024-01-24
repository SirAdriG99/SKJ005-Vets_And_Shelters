package org.vetsandshelters.customer.application.filterCustomer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.vetsandshelters.customer.domain.CustomerCollection;
import org.vetsandshelters.customer.domain.CustomerCriteria;
import org.vetsandshelters.customer.domain.CustomerRepository;
import org.vetsandshelters.shared.DomainUtils.Criteria.Filter;
import org.vetsandshelters.shared.DomainUtils.Criteria.FilterCollection;
import org.vetsandshelters.shared.DomainUtils.Criteria.FilterOperator;
import org.vetsandshelters.shared.DomainUtils.Pagination.Pagination;

@ApplicationScoped
public class FilterCustomerQueryHandler {
    @Inject
    private CustomerRepository repository;

    public FilterCustomerResponse handle(FilterCustomerQuery query) {
        CustomerCriteria criteria = this.setCriteria(query);

        CustomerCollection customers = this.repository.getBy(criteria);

        return new FilterCustomerResponse(customers.getCollection(), customers.getTotalElements());
    }

    private CustomerCriteria setCriteria(FilterCustomerQuery query) {
        /**
         * TODO.todo: Sort and order. Not for MVP
         */
        Pagination pagination = query.getSize() != null ? new Pagination(
                query.getSize(),
                query.getPageNumber() != null ? query.getPageNumber() : 0, null // Ignore sort by now
        ) : null;

        FilterCollection filters = new FilterCollection();
        if (query.getName() != null) {
            Filter nameFilter = new Filter("name", FilterOperator.LIKE, query.getName());
            filters.add(nameFilter);
        }
        if (query.getSurname() != null) {
            Filter surname = new Filter("surname", FilterOperator.LIKE, query.getSurname());
            filters.add(surname);
        }
        if (query.getUserAlias() != null) {
            Filter alias = new Filter("useralias", FilterOperator.LIKE, query.getUserAlias());
            filters.add(alias);
        }
        if (query.getDocNumber() != null) {
            Filter doc = new Filter("docnumber", FilterOperator.LIKE, query.getDocNumber());
            filters.add(doc);
        }
        if (query.getEmail() != null) {
            Filter email = new Filter("email", FilterOperator.LIKE,
                    query.getEmail());
            filters.add(email);
        }
        if (query.getPhone1() != null) {
            Filter phone1 = new Filter("phone1", FilterOperator.LIKE,
                    query.getPhone1());
            filters.add(phone1);
        }

        return new CustomerCriteria(filters, pagination);
    }

}
