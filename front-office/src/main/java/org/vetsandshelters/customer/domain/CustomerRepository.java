package org.vetsandshelters.customer.domain;

public interface CustomerRepository {
    public Customer getById(Integer id);

    public CustomerCollection getBy(CustomerCriteria criteria);

    public int store(Customer customer);

    public int update(Customer customer);
}
