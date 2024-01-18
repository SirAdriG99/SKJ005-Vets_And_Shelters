package org.vetsandshelters.customer.application.filterCustomer;

import org.vetsandshelters.customer.domain.Customer;

public class FilterCustomerResponse {
    private Customer[] customers;
    private int totalElements;

    public FilterCustomerResponse(Customer[] customers, int totalElements) {
        this.customers = customers;
        this.totalElements = totalElements;
    }

    public Customer[] getCustomers() {
        return customers;
    }

    public int getTotalElements() {
        return totalElements;
    }
}
