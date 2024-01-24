package org.vetsandshelters.shared.DomainUtils.Criteria;

// TODO.todo: Change implementation so it uses Collection
public class FilterCollection {
    private Filter[] filters;

    public FilterCollection(Filter[] filters) {
        this.filters = filters;
    }

    public FilterCollection() {
        Filter[] filters = {};
        this.filters = filters;
    }

    public void add(Filter filter) {
        Filter[] newFilters = new Filter[this.filters.length + 1];
        System.arraycopy(this.filters, 0, newFilters, 0, this.filters.length);
        newFilters[this.filters.length] = filter;
        this.filters = newFilters;
    }

    public Filter[] getFilters() {
        return this.filters;
    }

    public boolean isEmpty() {
        return this.filters == null || this.filters.length == 0;
    }

    public int size() {
        return this.filters.length;
    }

    public String toQueryWhere() {
        String queryWhere = "";
        if (!this.isEmpty()) {
            queryWhere += "WHERE ";
            for (int i = 0; i < this.filters.length; i++) {
                queryWhere += this.filters[i].toQueryOperation();
                if (i < this.filters.length - 1) {
                    queryWhere += " AND ";
                }
            }
        }
        return queryWhere;
    }
}
