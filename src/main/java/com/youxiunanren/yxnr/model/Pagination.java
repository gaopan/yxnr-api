package com.youxiunanren.yxnr.model;

public class Pagination {
    private int pageSize;
    private int pageIndex;
    private String sort;
    private String sortBy;

    public Pagination(){}

    public Pagination(int pageSize, int pageIndex, String sort, String sortBy){
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.sort = sort;
        this.sortBy = sortBy;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String toSql(){
        String sql = "";
        if(sort != null) {
            sql += "order by " + sort;
            if(sortBy != null) {
                sql += " " + sortBy + " ";
            }
        }
        if(pageIndex > 0 || pageSize > 0) {
            sql += "limit " + pageIndex + "," + pageSize;
        }
        return sql;
    }
}
