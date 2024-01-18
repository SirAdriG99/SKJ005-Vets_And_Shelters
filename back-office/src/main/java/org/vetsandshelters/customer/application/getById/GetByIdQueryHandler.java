package org.vetsandshelters.customer.application.getById;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.vetsandshelters.customer.domain.Customer;
import org.vetsandshelters.customer.domain.CustomerRepository;

@ApplicationScoped
public class GetByIdQueryHandler {

    @Inject
    private CustomerRepository repository;

    public GetByIdResponse handle(GetByIdQuery query) {
        int id = query.getId();

        Customer customer = this.repository.getById(id);

        return new GetByIdResponse(customer);
    }
}
