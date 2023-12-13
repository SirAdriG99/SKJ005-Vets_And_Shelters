package org.vetsandshelters.shared.DomainUtils.Pagination;

// TODO: Cambiar implementación para que use colección
public class SortCollection {
    private Sort[] sorts;

    public SortCollection(Sort[] sorts) {
        this.sorts = sorts;
    }

    public Sort[] getSorts() {
        return sorts;
    }
}
