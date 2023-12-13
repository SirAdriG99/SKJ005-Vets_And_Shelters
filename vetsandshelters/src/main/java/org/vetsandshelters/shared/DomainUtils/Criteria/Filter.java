package org.vetsandshelters.shared.DomainUtils.Criteria;

public class Filter<T> {
    private String field;
    private FilterOperator operator;
    private T value;

    public Filter(String field, FilterOperator operator, T value) {
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

    public T getValue() {
        return value;
    }
}
