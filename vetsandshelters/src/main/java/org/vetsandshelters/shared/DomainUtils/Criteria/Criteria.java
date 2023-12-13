package org.vetsandshelters.shared.DomainUtils.Criteria;

import org.vetsandshelters.shared.DomainUtils.Pagination.Pagination;

public class Criteria {
    private FilterCollection filters;
    private Pagination pagination;

    public Criteria(FilterCollection filters, Pagination pagination) {
        this.filters = filters;
        this.pagination = pagination;
    }

    public FilterCollection getFilters() {
        return filters;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public boolean hasFilters() {
        return filters != null;
    }

    public boolean hasPagination() {
        return pagination != null;
    }
}
