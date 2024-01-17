package org.vetsandshelters.shared.DomainUtils.Criteria;

import org.vetsandshelters.shared.DomainUtils.Pagination.Pagination;

/**
 * Criteria class represents a set of filters and pagination
 */
public class Criteria {
    /**
     * Filter collection can be empty
     */
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
