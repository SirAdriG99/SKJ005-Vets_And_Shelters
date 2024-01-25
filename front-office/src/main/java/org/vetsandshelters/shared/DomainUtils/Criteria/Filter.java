package org.vetsandshelters.shared.DomainUtils.Criteria;

public class Filter {
    private String field;
    private FilterOperator operator;
    private Object value; // TODO.todo: We should test this, but it should only contain primitive types,
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

    public String toQueryOperation() {
        String queryOperation = "";
        switch (this.operator) {
            case EQUALS, NOT_EQUALS, GREATER_THAN, GREATER_THAN_OR_EQUAL_TO, LESS_THAN, LESS_THAN_OR_EQUAL_TO:
                queryOperation += this.field + " " + this.getOperator().getOperator() + " " + this.value;
                break;
            case CONTAINS, LIKE:
                queryOperation += "UPPER(" + this.field + ") " + this.getOperator().getOperator() + " UPPER('%"
                        + this.value + "%')";
                break;
            case IN:
                queryOperation += this.field + " " + this.getOperator().getOperator() + " (" + this.value + ")";
                break;
            case NOT_IN:
                queryOperation += this.field + " " + this.getOperator().getOperator() + " (" + this.value + ")";
                break;
        }
        return queryOperation;
    }
}
