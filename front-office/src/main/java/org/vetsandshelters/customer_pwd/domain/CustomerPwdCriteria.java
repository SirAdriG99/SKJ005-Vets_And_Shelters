package org.vetsandshelters.customer_pwd.domain;

import org.vetsandshelters.shared.DomainUtils.Criteria.Criteria;
import org.vetsandshelters.shared.DomainUtils.Criteria.FilterCollection;
import org.vetsandshelters.shared.DomainUtils.Pagination.Pagination;

public class CustomerPwdCriteria extends Criteria {

    public CustomerPwdCriteria(FilterCollection filters, Pagination pagination) {
        super(filters, pagination);
    }

    /**
     * Returns the allowed fields for filtering
     * 
     * @return
     */
    public String[] getAllowedFields() {
        return new String[] {
                "id",
                "customer_id",
                "pwd",
        };
    }
}
