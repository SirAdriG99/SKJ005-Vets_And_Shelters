package org.vetsandshelters.front_office.customer.application.showCustomer;

import org.vetsandshelters.front_office.customer.domain.Customer;

public class ShowCustomerResponse {
    public Customer customer;

    public ShowCustomerResponse(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
