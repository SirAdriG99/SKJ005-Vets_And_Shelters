package org.vetsandshelters.shared.DomainUtils.Pagination;

public enum Sort {
    ASCENDING("ASC"),
    DESCENDING("DESC");

    private String value;

    Sort(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
