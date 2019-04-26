package com.youxiunanren.yxnr.db;

import com.youxiunanren.yxnr.db.core.filter.ComparablePair;
import com.youxiunanren.yxnr.db.core.filter.RelatablePair;
import com.youxiunanren.yxnr.model.Pagination;

import java.util.List;
import java.util.Map;

public interface DataSource {

    public <T> List<T> findAll(Class<T> tClass, ComparablePair cp);

    public <T> List<T> findAll(Class<T> tClass, RelatablePair rp);

    public <T> List<T> findByPagination(Class<T> tClass, ComparablePair cp, Pagination pagination);

    public <T> List<T> findByPagination(Class<T> tClass, RelatablePair rp, Pagination pagination);

    public <T> long count(Class<T> tClass, ComparablePair cp);

    public <T> long count(Class<T> tClass, RelatablePair rp);

    public <T> boolean create(Class<T> tClass, T t);

    public <T> boolean update(Class<T> tClass, String id, Map<String, Object> updates);

    public <T> boolean update(Class<T> tClass, String id, T t);

    public <T> boolean delete(Class<T> tClass, String id);

    public <T> T find(Class<T> tClass, String id);

}
