package org.vetsandshelters.shared.DomainUtils.Pagination;

public class Pagination {
    private int offset;
    private int size;
    private SortCollection sort;

    public Pagination(int offset, int size, SortCollection sort) {
        this.offset = offset;
        this.size = size;
        this.sort = sort;
    }

    public int getOffset() {
        return offset;
    }

    public int getSize() {
        return size;
    }

    public SortCollection getSort() {
        return sort;
    }
}
