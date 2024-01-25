package org.vetsandshelters.customer.application.showCustomer;

import org.vetsandshelters.customer.domain.Customer;

public class ShowCustomerResponse {
    public Customer customer;

    public ShowCustomerResponse(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

}
