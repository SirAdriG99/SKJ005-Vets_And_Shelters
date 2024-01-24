package org.vetsandshelters.customer.domain;

import org.vetsandshelters.shared.DomainUtils.Criteria.Criteria;
import org.vetsandshelters.shared.DomainUtils.Criteria.FilterCollection;
import org.vetsandshelters.shared.DomainUtils.Pagination.Pagination;

public class CustomerCriteria extends Criteria {

    public CustomerCriteria(FilterCollection filters, Pagination pagination) {
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
                "doc_type_id",
                "doc_number",
                "name",
                "surname",
                "user_alias",
                "date_birth",
                "banned",
                "email",
                "phone1",
                "phone2",
                "address",
        };
    }
}
