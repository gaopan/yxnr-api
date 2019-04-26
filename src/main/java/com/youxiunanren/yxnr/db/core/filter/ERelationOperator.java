package com.youxiunanren.yxnr.db.core.filter;

public enum ERelationOperator {
    And("and"), Or("or");

    private final String operator;

    ERelationOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return this.operator;
    }
}
