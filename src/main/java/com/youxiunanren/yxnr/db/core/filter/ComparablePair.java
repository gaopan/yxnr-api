package com.youxiunanren.yxnr.db.core.filter;

public class ComparablePair {
    private String field;
    private Object value;
    private ECompareOperator operator;

    public ComparablePair(){}

    public ComparablePair(ECompareOperator operator, String field, Object value) {
        this.operator = operator;
        this.field = field;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public ECompareOperator getOperator() {
        return operator;
    }

    public void setOperator(ECompareOperator operator) {
        this.operator = operator;
    }
}
