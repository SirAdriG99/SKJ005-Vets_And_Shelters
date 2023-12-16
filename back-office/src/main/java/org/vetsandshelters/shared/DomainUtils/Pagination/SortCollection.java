package org.vetsandshelters.shared.DomainUtils.Pagination;

// TODO: Change implementation so it uses Collection
public class SortCollection {
    private Sort[] sorts;

    public SortCollection(Sort[] sorts) {
        this.sorts = sorts;
    }

    public Sort[] getSorts() {
        return sorts;
    }

    public boolean isEmpty() {
        return sorts == null || sorts.length == 0;
    }
}
