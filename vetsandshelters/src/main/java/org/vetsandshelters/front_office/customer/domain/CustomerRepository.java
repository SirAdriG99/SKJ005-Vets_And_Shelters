package org.vetsandshelters.front_office.customer.domain;

public interface CustomerRepository {
    public Customer getById(Integer id);

    public CustomerCollection getBy(CustomerCriteria criteria);

    public int add(Customer customer);

    public int update(Customer customer);
}
