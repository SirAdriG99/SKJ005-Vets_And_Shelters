package org.vetsandshelters.customer.domain;

import org.vetsandshelters.shared.DomainUtils.Criteria.Criteria;
import org.vetsandshelters.shared.DomainUtils.Criteria.FilterCollection;
import org.vetsandshelters.shared.DomainUtils.Pagination.Pagination;

public class CustomerCriteria extends Criteria {

    public CustomerCriteria(FilterCollection filters, Pagination pagination) {
        super(filters, pagination);
        // TODO Auto-generated constructor stub
    }

    public String[] getAllowedFields() {
        return new String[] {
                "id",
                "name",
                "surname",
                "userAlias",
                "docNumber",
                "email",
                "phone1"
        };
    }

}
