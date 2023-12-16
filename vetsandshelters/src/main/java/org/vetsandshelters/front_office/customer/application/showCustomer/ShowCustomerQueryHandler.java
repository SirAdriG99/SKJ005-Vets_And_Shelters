package org.vetsandshelters.front_office.customer.application.showCustomer;

import jakarta.inject.Inject;
import org.vetsandshelters.front_office.customer.domain.Customer;
import org.vetsandshelters.front_office.customer.domain.CustomerRepository;

public class ShowCustomerQueryHandler {

    /**
     * It exists the posibility of using more than 1 repository in the same handler
     */

    private CustomerRepository repository;

    @Inject
    public ShowCustomerQueryHandler(CustomerRepository repository) {
        this.repository = repository;
    }

    public ShowCustomerResponse handle(ShowCustomerQuery query) {
        int id = query.getId();

        Customer customer = this.repository.getById(id);

        return new ShowCustomerResponse(customer);
    }
}
