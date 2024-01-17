package org.vetsandshelters.shared.DomainUtils.Criteria;

public class Filter {
    private String field;
    private FilterOperator operator;
    private Object value; // TODO: We should test this, but it should only contain primitive types,
    // String, Date (or similar) or arrays of those types and the FilterCollection
    // should contain filters with different data types
    // e.g. a filter with field "name" and value "pepe" and another filter with
    // field "age" and value 22

    public Filter(String field, FilterOperator operator, Object value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public FilterOperator getOperator() {
        return operator;
    }

    public Object getValue() {
        return value;
    }
}
