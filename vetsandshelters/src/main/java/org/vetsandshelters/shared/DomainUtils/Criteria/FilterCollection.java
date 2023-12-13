package org.vetsandshelters.shared.DomainUtils.Criteria;

// TODO: Cambiar implementación para
public class FilterCollection {
    private Filter[] filters;

    public FilterCollection(Filter[] filters) {
        this.filters = filters;
    }

    public Filter[] getFilters() {
        return filters;
    }
}
