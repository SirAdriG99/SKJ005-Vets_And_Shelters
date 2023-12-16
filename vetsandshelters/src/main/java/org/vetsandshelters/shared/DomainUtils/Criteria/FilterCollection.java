package org.vetsandshelters.shared.DomainUtils.Criteria;

import java.util.Collection;

// TODO: Change implementation so it uses Collection
public class FilterCollection implements Collection {
    private Filter<?>[] filters;

    public FilterCollection(Filter<?>[] filters) {
        this.filters = filters;
    }

    public Filter<?>[] getFilters() {
        return filters;
    }

    public boolean isEmpty() {
        return filters == null || filters.length == 0;
    }
}
