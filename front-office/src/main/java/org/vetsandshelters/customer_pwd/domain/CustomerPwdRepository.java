package org.vetsandshelters.customer_pwd.domain;

public interface CustomerPwdRepository {
    public CustomerPwd getById(int id);
    public CustomerPwd getByCustomerId(int customer_id);

//    public CustomerPwdCollection getBy(CustomerPwdCriteria criteria);

    public int store(CustomerPwd customerPwd);

    public int update(CustomerPwd customerPwd);
}
