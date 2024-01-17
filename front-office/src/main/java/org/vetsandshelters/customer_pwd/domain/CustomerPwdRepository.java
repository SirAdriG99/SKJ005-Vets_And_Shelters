package org.vetsandshelters.customer_pwd.domain;

public interface CustomerPwdRepository {
    public CustomerPwd getById(Integer id);

    public CustomerPwdCollection getBy(CustomerPwdCriteria criteria);

    public int add(CustomerPwd customerPwd);

    public int update(CustomerPwd customerPwd);
}
