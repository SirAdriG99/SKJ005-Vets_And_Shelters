package org.vetsandshelters.customer_pwd.application.showCustomerPwd;

import jakarta.inject.Inject;
import org.vetsandshelters.customer.domain.Customer;
import org.vetsandshelters.customer.domain.CustomerRepository;
import org.vetsandshelters.customer.infraestructurre.CustomerPwdRepositoryFake;

public class ShowCustomerPwdQueryHandler {

    /**
     * It exists the posibility of using more than 1 repository in the same handler
     */

    private CustomerPwdRepositoryFake repository;

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
