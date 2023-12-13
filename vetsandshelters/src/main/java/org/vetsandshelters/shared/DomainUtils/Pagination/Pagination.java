package org.vetsandshelters.shared.DomainUtils.Pagination;

public class Pagination {
    private int page;
    private int size;
    private SortCollection sort;

    public Pagination(int page, int size, SortCollection sort) {
        this.page = page;
        this.size = size;
        this.sort = sort;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public SortCollection getSort() {
        return sort;
    }
}
