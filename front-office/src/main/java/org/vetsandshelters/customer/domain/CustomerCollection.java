package org.vetsandshelters.customer.domain;

import org.vetsandshelters.shared.DomainUtils.Collection;

/*
 * The collections represent a group of objects of the same type.
 * They're all extensions of the Collection class.
 * The collection class has all the methods that we're going to need
 */
public class CustomerCollection extends Collection<Customer> {
    public CustomerCollection(Customer[] customers) {
        super(customers);
    }

    public CustomerCollection(Customer[] customers, int total) {
        super(customers, total);
    }
}
