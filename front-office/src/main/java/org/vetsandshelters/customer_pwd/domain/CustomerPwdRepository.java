package org.vetsandshelters.front_office.customer.domain;

public interface CustomerPwdRepository {
    public CustomerPwd getById(Integer id);

    public CustomerPwdCollection getBy(CustomerPwdCriteria criteria);

    public int add(CustomerPwd customerPwd);

    public int update(CustomerPwd customerPwd);
}
