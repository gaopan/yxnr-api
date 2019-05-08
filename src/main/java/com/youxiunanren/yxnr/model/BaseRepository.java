package com.youxiunanren.yxnr.model;

import com.youxiunanren.yxnr.db.DataAccessObject;
import com.youxiunanren.yxnr.db.core.filter.ComparablePair;
import com.youxiunanren.yxnr.db.core.filter.RelatablePair;
import com.youxiunanren.yxnr.util.RandomUtil;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

public class BaseRepository<T extends DataPersistEntity> {

    final Class<T> clazz;

    @Inject
    DataAccessObject dao;

    public BaseRepository(Class<T> clazz){
        this.clazz = clazz;
    }

    public List<T> findAll(RelatablePair rp){
        return dao.findAll(clazz, rp);
    }

    public List<T> findAll(ComparablePair cp){
        return dao.findAll(clazz, cp);
    }

    public List<T> findByPagination(RelatablePair rp, Pagination pagination){
        return dao.findByPagination(clazz, rp, pagination);
    }

    public List<T> findByPagination(ComparablePair cp, Pagination pagination){
        return dao.findByPagination(clazz, cp, pagination);
    }

    public long count(RelatablePair rp){
        return dao.count(clazz, rp);
    }

    public long count(ComparablePair cp){
        return dao.count(clazz, cp);
    }

    public T find(String id){
        return dao.find(clazz, id);
    }

    public boolean create(T t){
        return dao.create(clazz, t);
    }

    public boolean update(String id, T t){
        return dao.update(clazz, id, t);
    }

    public boolean delete(String id){
        return dao.delete(clazz, id);
    }

    public boolean update(String id, Map<String, Object> updates){
        return dao.update(clazz, id, updates);
    }

    public DataAccessObject getDataSource() {
        return dao;
    }

    public String generateId(){
        return RandomUtil.unique();
    }
}
