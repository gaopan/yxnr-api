package com.youxiunanren.yxnr.model;

public class Pagination {
    private int pageSize;
    private int pageIndex;
    private String sort;
    private String sortBy;
    private String filter;

    public Pagination(){}

    public Pagination(int pageSize, int pageIndex, String sort, String sortBy, String filter){
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.sort = sort;
        this.sortBy = sortBy;
        this.filter = filter;
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

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
