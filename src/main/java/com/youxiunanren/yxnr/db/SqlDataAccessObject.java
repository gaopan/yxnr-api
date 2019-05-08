package com.youxiunanren.yxnr.db;

import java.util.List;

public interface SqlDataAccessObject extends DataAccessObject {

    public <T> boolean executeUpdate(Class<T> tClass, String sql);

    public <T> List<T> executeQuery(Class<T> tClass, String sql);
}
