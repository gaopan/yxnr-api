package com.youxiunanren.yxnr.model;

import com.youxiunanren.yxnr.db.DataSource;
import com.youxiunanren.yxnr.db.core.filter.ComparablePair;
import com.youxiunanren.yxnr.db.core.filter.RelatablePair;
import com.youxiunanren.yxnr.db.mysql.MysqlManager;

import java.util.List;
import java.util.Map;

public class BaseRepository<T extends DataPersistEntity> {

    final Class<T> clazz;

    private DataSource ds = new MysqlManager();

    public BaseRepository(Class<T> clazz){
        this.clazz = clazz;
    }

    public List<T> findAll(RelatablePair rp){
        return ds.findAll(clazz, rp);
    }

    public List<T> findAll(ComparablePair cp){
        return ds.findAll(clazz, cp);
    }

    public List<T> findByPagination(RelatablePair rp, Pagination pagination){
        return ds.findByPagination(clazz, rp, pagination);
    }

    public List<T> findByPagination(ComparablePair cp, Pagination pagination){
        return ds.findByPagination(clazz, cp, pagination);
    }

    public long count(RelatablePair rp){
        return ds.count(clazz, rp);
    }

    public long count(ComparablePair cp){
        return ds.count(clazz, cp);
    }

    public T find(String id){
        return ds.find(clazz, id);
    }

    public boolean create(T t){
        return ds.create(clazz, t);
    }

    public boolean update(String id, T t){
        return ds.update(clazz, id, t);
    }

    public boolean delete(String id){
        return ds.delete(clazz, id);
    }

    public boolean update(String id, Map<String, Object> updates){
        return ds.update(clazz, id, updates);
    }

    public DataSource getDataSource() {
        return ds;
    }
}
