package org.vetsandshelters.shared.DomainUtils.Pagination;

public class Pagination {
    /**
     * SortCollection can be empty
     */
    private int pageNumber;
    private int size;
    private SortCollection sort;

    public Pagination(int offset, int size, SortCollection sort) {
        this.pageNumber = offset;
        this.size = size;
        this.sort = sort;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getSize() {
        return size;
    }

    public SortCollection getSort() {
        return sort;
    }
}
