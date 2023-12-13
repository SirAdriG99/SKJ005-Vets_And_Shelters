package org.vetsandshelters.shared.DomainUtils.Pagination;

public class Sort {

    private String value; // By what field are we going to sort
    private Order order; // The order

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
