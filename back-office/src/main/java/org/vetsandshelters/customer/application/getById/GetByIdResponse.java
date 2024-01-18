package org.vetsandshelters.customer.application.getById;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.vetsandshelters.customer.domain.Customer;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class GetByIdResponse {
    private Customer customer;

    public GetByIdResponse(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
