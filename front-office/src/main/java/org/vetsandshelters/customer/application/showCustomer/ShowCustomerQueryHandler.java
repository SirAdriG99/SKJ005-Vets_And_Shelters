package org.vetsandshelters.customer.application.showCustomer;

import jakarta.inject.Inject;
import org.vetsandshelters.customer.domain.Customer;
import org.vetsandshelters.customer.domain.CustomerRepository;

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
/*
    // TODO: The function.
    private String getPasswordBy(final int customerId) {

        return null;

    }

    // TODO: Check if it's correct.
    private boolean checkPassword(final int customerId,  final String writtenPassword){
        String dbPassword = getPasswordBy(customerId);
        // TODO: if it's necessary to do something to the password received of the DB, do it here.
        //  if not, change dbPassword to "final".
        return (dbPassword != null) && (dbPassword.equals(writtenPassword));
    }
*/
}
