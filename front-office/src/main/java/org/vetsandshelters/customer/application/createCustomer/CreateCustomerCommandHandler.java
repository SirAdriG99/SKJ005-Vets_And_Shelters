package org.vetsandshelters.customer.application.createCustomer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.vetsandshelters.customer.domain.Customer;
import org.vetsandshelters.customer.domain.CustomerRepository;
import org.vetsandshelters.customer_pwd.domain.CustomerPwd;
import org.vetsandshelters.customer_pwd.domain.CustomerPwdRepository;

@ApplicationScoped
public class CreateCustomerCommandHandler {
    @Inject
    private CustomerRepository repository;
    @Inject
    private CustomerPwdRepository customerPwdRepository;

    public CreateCustomerResponse handle(CreateCustomerCommand command) {
        Customer customer = new Customer(
                null,
                command.getDoc_number(),
                command.getName(),
                command.getSurname(),
                command.getUser_alias(),
                command.getDate_birth(),
                false,
                command.getEmail(),
                command.getPhone1(),
                command.getPhone2(),
                command.getAddress()
        );

        int result = this.repository.store(customer);

        // The next code should be calling a event and the customerPwd should hava a eventListener in the application layer to do this action
        CustomerPwd customerPwd = new CustomerPwd(
                null,
                result,
                command.getPassword()
        );
        int result2 = this.customerPwdRepository.store(customerPwd);

        return new CreateCustomerResponse(result);
    }
}
