package org.vetsandshelters.front_office.customer.application.showCustomer;

import jakarta.inject.Inject;
import org.vetsandshelters.front_office.customer.domain.Customer;
import org.vetsandshelters.front_office.customer.domain.CustomerRepository;

public class ShowCustomerPwdQueryHandler {

    /**
     * It exists the posibility of using more than 1 repository in the same handler
     */

    private CustomerPwdRepository repository;

    @Inject
    public ShowCustomerPwdQueryHandler(CustomerPwdRepository repository) {
        this.repository = repository;
    }

    public ShowCustomerPwdResponse handle(ShowCustomerPwdQuery query) {
        int id = query.getId();

        CustomerPwd customerPwd = this.repository.getById(id);

        return new ShowCustomerResponse(customerPwd);
    }
}
