package org.vetsandshelters.shared.DomainUtils.Pagination;

public class Sort {

    private String value;
    private Order order;

    Sort(String value, Order order) {
        this.value = value;
        this.order = order;
    }

    public String getValue() {
        return value;
    }

    public Order getOrder() {
        return order;
    }
}
