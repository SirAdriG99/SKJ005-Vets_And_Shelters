package org.vetsandshelters.front_office.customer.application.showCustomer;

import org.vetsandshelters.front_office.customer.domain.Customer;

public class ShowCustomerPwdResponse {
    public CustomerPwd customerPwd;

    public ShowCustomerPwdResponse(CustomerPwd customerPwd) {
        this.customerPwd = customerPwd;
    }

    public CustomerPwd getCustomerPwd() {
        return customerPwd;
    }

}
