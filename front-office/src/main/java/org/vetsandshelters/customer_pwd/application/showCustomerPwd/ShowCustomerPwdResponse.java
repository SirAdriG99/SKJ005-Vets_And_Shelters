package org.vetsandshelters.customer_pwd.application.showCustomerPwd;


import org.vetsandshelters.customer_pwd.domain.CustomerPwd;

public class ShowCustomerPwdResponse {
    public CustomerPwd customerPwd;

    public ShowCustomerPwdResponse(CustomerPwd customerPwd) {
        this.customerPwd = customerPwd;
    }

    public CustomerPwd getCustomerPwd() {
        return customerPwd;
    }

}
