package org.vetsandshelters.shared.DomainUtils.Criteria;

public enum FilterOperator {
    // Constants for each operator available to compare values
    EQUALS("="),
    NOT_EQUALS("!="),
    GREATER_THAN(">"),
    GREATER_THAN_OR_EQUAL_TO(">="),
    LESS_THAN("<"),
    LESS_THAN_OR_EQUAL_TO("<="),
    IN("IN"),
    NOT_IN("NOT IN"),
    CONTAINS("CONTAINS"),
    LIKE("LIKE");

    private String operator;

    private FilterOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

}
