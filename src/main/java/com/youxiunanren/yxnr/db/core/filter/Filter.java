package com.youxiunanren.yxnr.db.core.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Filter {

    private static Logger logger = LoggerFactory.getLogger(Filter.class);

    public static ComparablePair bt(String field, Object value){
        return new ComparablePair(ECompareOperator.BT, field, value);
    }

    public static ComparablePair lt(String field, Object value){
        return new ComparablePair(ECompareOperator.LT, field, value);
    }

    public static ComparablePair be(String field, Object value){
        return new ComparablePair(ECompareOperator.BET, field, value);
    }

    public static ComparablePair le(String field, Object value){
        return new ComparablePair(ECompareOperator.LET, field, value);
    }

    public static ComparablePair eq(String field, Object value){
        return new ComparablePair(ECompareOperator.EQ, field, value);
    }

    public static ComparablePair contains(String field, String value){
        return new ComparablePair(ECompareOperator.CONTAINS, field, value);
    }

    public static RelatablePair or(ComparablePair left, ComparablePair right){
        return new RelatablePair(ERelationOperator.Or, left, right);
    }

    public static RelatablePair and(ComparablePair left, ComparablePair right){
        return new RelatablePair(ERelationOperator.And, left, right);
    }

    public static String toSql(ComparablePair cp){
        if(cp == null) return null;
        if(cp.getField() == null) {
            logger.warn("Field should not be null");
            return null;
        }
        if(cp.getOperator() == null) {
            logger.warn("Operator should not be null");
            return null;
        }
        if(!ECompareOperator.CONTAINS.equals(cp.getOperator())) {
            String sql = cp.getField() + " " + cp.getOperator().toString() + " ";
            Object value = cp.getValue();
            if(value instanceof String) {
                sql += "'" + value + "'";
            } else {
                sql += value;
            }
            return sql;
        } else {
            String sql = cp.getField() + " like ";
            Object value = cp.getValue();
            if(value instanceof String) {
                sql += "'%" + value + "%'";
            } else {
                sql += value;
            }
            return sql;
        }
    }

    public static String toSql(RelatablePair rp){
        if(rp == null) return null;
        if(rp.getOperator() == null) {
            logger.warn("Operation should not be null");
        }
        String leftCondition = toSql(rp.getLeft()), rightCondition = toSql(rp.getRight());
        if(leftCondition == null || rightCondition == null) {
            logger.warn("Either left condition or right condition is not valid");
            return null;
        }
        return leftCondition + " " + rp.getOperator().toString() + " " + rightCondition;
    }

    public static void main(String[] args) {
        System.out.println(Filter.toSql(Filter.eq("name", null)));
        System.out.println(Filter.toSql(Filter.and(Filter.eq("name", "abc"), Filter.bt("age", 12))));
    }

}
