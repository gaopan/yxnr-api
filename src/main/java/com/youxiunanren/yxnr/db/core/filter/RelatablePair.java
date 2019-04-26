package com.youxiunanren.yxnr.db.core.filter;

public class RelatablePair {
    private ComparablePair left;
    private ComparablePair right;
    private ERelationOperator operator;

    public RelatablePair(){}

    public RelatablePair(ERelationOperator operator, ComparablePair left, ComparablePair right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public ComparablePair getLeft() {
        return left;
    }

    public void setLeft(ComparablePair left) {
        this.left = left;
    }

    public ComparablePair getRight() {
        return right;
    }

    public void setRight(ComparablePair right) {
        this.right = right;
    }

    public ERelationOperator getOperator() {
        return operator;
    }

    public void setOperator(ERelationOperator operator) {
        this.operator = operator;
    }
}
