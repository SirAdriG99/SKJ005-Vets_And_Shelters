package org.vetsandshelters.customer_pwd.domain;


import org.vetsandshelters.shared.DomainUtils.Collection;

/*
 * The collections represent a group of objects of the same type.
 * They're all extensions of the Collection class.
 * The collection class has all the methods that we're going to need
 */
public class CustomerPwdCollection extends Collection<CustomerPwd> {
    public CustomerPwdCollection(CustomerPwd[] customerPwds) {
        super(customerPwds);
    }

    public CustomerPwdCollection(CustomerPwd[] customerPwds, int total) {
        super(customerPwds, total);
    }
}
