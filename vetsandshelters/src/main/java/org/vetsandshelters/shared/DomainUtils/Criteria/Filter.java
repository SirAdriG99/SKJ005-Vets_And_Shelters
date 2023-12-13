package org.vetsandshelters.shared.DomainUtils.Criteria;

public class Filter {
    private String field;
    private FilterOperator operator;
    private Object value;

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

    public String getValue() {
        return value.toString();
    }
}
