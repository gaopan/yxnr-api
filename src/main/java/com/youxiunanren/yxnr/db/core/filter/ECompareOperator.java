package com.youxiunanren.yxnr.db.core.filter;

public enum ECompareOperator {
    BT(">"), LT("<"), EQ("="), BET(">="), LET("<="), CONTAINS("contains");
    private final String operator;

    ECompareOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return this.operator;
    }
}
