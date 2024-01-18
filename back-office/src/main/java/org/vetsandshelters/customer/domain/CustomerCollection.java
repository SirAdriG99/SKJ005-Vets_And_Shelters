package org.vetsandshelters.customer.domain;

import org.vetsandshelters.shared.DomainUtils.Collection;

public class CustomerCollection extends Collection<Customer> {

    public CustomerCollection(Customer[] collection) {
        super(collection);
    }

}
