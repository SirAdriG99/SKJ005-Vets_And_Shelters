package org.vetsandshelters.customer_pwd.application.showCustomerPwd;

import jakarta.inject.Inject;
import org.vetsandshelters.customer_pwd.domain.CustomerPwdRepository;
import org.vetsandshelters.customer_pwd.domain.CustomerPwd;

public class ShowCustomerPwdQueryHandler {

    /**
     * It exists the posibility of using more than 1 repository in the same handler
     */

    @Inject
    private CustomerPwdRepository repository;

    public ShowCustomerPwdResponse handle(ShowCustomerPwdQuery query) {
        int id = query.getId();

        CustomerPwd customerPwd = this.repository.getById(id);

        return new ShowCustomerPwdResponse(customerPwd);
    }
}
