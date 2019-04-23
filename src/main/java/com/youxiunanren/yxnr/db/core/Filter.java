package com.youxiunanren.yxnr.db.core;

public class Filter {
    public static <T> ComparablePair<T> bt(String field, T value){
        return new ComparablePair<>(ECompareOperator.BT, field, value);
    }

    public static <T> ComparablePair<T> lt(String field, T value){
        return new ComparablePair<>(ECompareOperator.LT, field, value);
    }

    public static <T> ComparablePair<T> be(String field, T value){
        return new ComparablePair<>(ECompareOperator.BET, field, value);
    }

    public static <T> ComparablePair<T> le(String field, T value){
        return new ComparablePair<>(ECompareOperator.LET, field, value);
    }

    public static <T> ComparablePair<T> eq(String field, T value){
        return new ComparablePair<>(ECompareOperator.EQ, field, value);
    }

    public static RelatablePair or(ComparablePair left, ComparablePair right){
        return new RelatablePair(ERelationOperator.Or, left, right);
    }

    public static RelatablePair and(ComparablePair left, ComparablePair right){
        return new RelatablePair(ERelationOperator.And, left, right);
    }

    public static enum ECompareOperator {
        BT(">"), LT("<"), EQ("="), BET(">="), LET("<=");

        private final String operator;

        ECompareOperator(String operator) {
            this.operator = operator;
        }

        @Override
        public String toString() {
            return this.operator;
        }
    }

    public static enum ERelationOperator {
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

    public static class RelatablePair {
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

    public static class ComparablePair<T> {
        private String field;
        private T value;
        private ECompareOperator operator;

        public ComparablePair(){}

        public ComparablePair(ECompareOperator operator, String field, T value) {
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

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public ECompareOperator getOperator() {
            return operator;
        }

        public void setOperator(ECompareOperator operator) {
            this.operator = operator;
        }
    }
}
