package com.youxiunanren.yxnr.db.mysql;

import com.youxiunanren.yxnr.db.SqlDataSource;
import com.youxiunanren.yxnr.db.core.filter.ComparablePair;
import com.youxiunanren.yxnr.db.core.filter.Filter;
import com.youxiunanren.yxnr.db.core.filter.RelatablePair;
import com.youxiunanren.yxnr.model.Pagination;
import com.youxiunanren.yxnr.model.annotation.ID;
import com.youxiunanren.yxnr.model.annotation.PoRequired;
import com.youxiunanren.yxnr.modules.authentication.models.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MysqlManager implements SqlDataSource {
    private static Logger logger = LoggerFactory.getLogger(MysqlManager.class);

    public static void loadDriver(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            logger.error("Failed to load mysql driver", e);
        }
    }

    private Connection obtainConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yxnr_auth?user=root&password=rat.aid.two-666&useSSL=false");
        } catch (SQLException e) {
            logger.error("Failed to obtain mysql connection", e);
        }
        return conn;
    }

    public <T> List<T> findAll(Class<T> tClass, ComparablePair cp) {
        return executeQuery(tClass, "select * from " + tClass.getSimpleName() + " where " + Filter.toSql(cp));
    }

    public <T> List<T> findAll(Class<T> tClass, RelatablePair rp) {
        return executeQuery(tClass, "select * from " + tClass.getSimpleName() + " where " + Filter.toSql(rp));
    }

    public <T> List<T> findByPagination(Class<T> tClass, ComparablePair cp, Pagination pagination){
        return executeQuery(tClass, "select * from " + tClass.getSimpleName() + " where " + Filter.toSql(cp) + " " + pagination.toSql());
    }

    public <T> List<T> findByPagination(Class<T> tClass, RelatablePair rp, Pagination pagination){
        return executeQuery(tClass, "select * from " + tClass.getSimpleName() + " where " + Filter.toSql(rp) + " " + pagination.toSql());
    }

    @Override
    public <T> long count(Class<T> tClass, ComparablePair cp) {
        return executeQueryForCount(tClass, "count", "select count(*) as count from " + tClass.getSimpleName() + " where " + Filter.toSql(cp));
    }

    @Override
    public <T> long count(Class<T> tClass, RelatablePair rp) {
        return executeQueryForCount(tClass, "count", "select count(*) as count from " + tClass.getSimpleName() + " where " + Filter.toSql(rp));
    }

    public <T> boolean create(Class<T> tClass, T t){
        if(tClass == null) return false;
        Field[] fields = tClass.getDeclaredFields();
        Method[] methods = tClass.getDeclaredMethods();
        String tableName = tClass.getSimpleName().toLowerCase();
        ArrayList<String> columns = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        try {
            for(Field field : fields) {
                if(field.getDeclaredAnnotation(PoRequired.class) != null) {
                    columns.add(field.getName());
                    for(Method method : methods) {
                        String methodName = method.getName();
                        if(methodName.startsWith("get") && methodName.substring(3).toLowerCase().equals(field.getName().toLowerCase())) {
                            Object value = method.invoke(t);
                            if(value == null) {
                                values.add(null);
                            } else if(value instanceof Number){
                                values.add(value.toString());
                            } else {
                                values.add("'" + value.toString() + "'");
                            }
                            break;
                        }
                    }
                }
            }
        } catch(Exception e){
            logger.error("Unexpected error", e);
        }

        return executeUpdate(tClass, "insert into " + tableName + " (" + String.join(",", columns) + ") values(" + String.join(",", values) + ")");
    }

    public <T> boolean update(Class<T> tClass, String id, Map<String, Object> updates){
        if(tClass == null || updates == null || updates.size() < 1) return false;
        Field[] fields = tClass.getDeclaredFields();
        String idFieldName = null, tableName = tClass.getSimpleName().toLowerCase();
        ArrayList<String> assignList = new ArrayList<>();
        try {
            for(Field field : fields) {
                if(field.getDeclaredAnnotation(ID.class) != null) {
                    idFieldName = field.getName();
                }
                if(field.getDeclaredAnnotation(PoRequired.class) != null && field.getDeclaredAnnotation(ID.class) == null) {
                    if(updates.keySet().contains(field.getName())) {
                        Object value = updates.get(field.getName());
                        if(value == null) {
                            assignList.add(field.getName() + "=" + null);
                        } else if(value instanceof Number){
                            assignList.add(field.getName() + "=" + value.toString());
                        } else {
                            assignList.add(field.getName() + "='" + value.toString() + "'");
                        }
                    }
                }
            }
        } catch(Exception e){
            logger.error("Unexpected error", e);
        }
        if(idFieldName == null) return false;

        return executeUpdate(tClass, "update " + tableName + " set " + String.join(",", assignList) + " where " + idFieldName + "='" + id + "'");
    }

    public <T> boolean update(Class<T> tClass, String id, T t){
        if(tClass == null) return false;
        Field[] fields = tClass.getDeclaredFields();
        Method[] methods = tClass.getDeclaredMethods();
        String idFieldName = null, tableName = tClass.getSimpleName().toLowerCase();
        ArrayList<String> assignList = new ArrayList<>();
        try {
            for(Field field : fields) {
                if(field.getDeclaredAnnotation(ID.class) != null) {
                    idFieldName = field.getName();
                }
                if(field.getDeclaredAnnotation(PoRequired.class) != null && field.getDeclaredAnnotation(ID.class) == null) {
                    for(Method method : methods) {
                        String methodName = method.getName();
                        if(methodName.startsWith("get") && methodName.substring(3).toLowerCase().equals(field.getName().toLowerCase())) {
                            Object value = method.invoke(t);
                            if(value == null) {
                                assignList.add(field.getName() + "=" + null);
                            } else if(value instanceof Number){
                                assignList.add(field.getName() + "=" + value.toString());
                            } else {
                                assignList.add(field.getName() + "='" + value.toString() + "'");
                            }
                            break;
                        }
                    }
                }
            }
        } catch(Exception e){
            logger.error("Unexpected error", e);
        }
        if(idFieldName == null) return false;

        return executeUpdate(tClass, "update " + tableName + " set " + String.join(",", assignList) + " where " + idFieldName + "='" + id + "'");
    }

    public <T> boolean delete(Class<T> tClass, String id) {
        if(tClass == null) return false;
        Field[] fields = tClass.getDeclaredFields();
        String idFieldName = null, tableName = tClass.getSimpleName().toLowerCase();
        for(Field field : fields) {
            if(field.getDeclaredAnnotation(ID.class) != null) {
                idFieldName = field.getName();
                break;
            }
        }
        if(idFieldName == null) return false;

        return executeUpdate(tClass, "delete from " + tableName + " where " + idFieldName + "='" + id + "'");
    }

    private void closeDBResource(PreparedStatement stmt, Connection conn, ResultSet rs){
        if(rs != null) {
            try{
                rs.close();
            } catch(SQLException sqlEx) {
                logger.error("Close Mysql ResultSet error", sqlEx);
            }
            rs = null;
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException sqlEx) {
                logger.error("Close Mysql Statement error", sqlEx);
            }
            stmt = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException sqlEx) {
                logger.error("Close Mysql Statement error", sqlEx);
            }
            conn = null;
        }
    }

    public <T> boolean executeUpdate(Class<T> tClass, String sql){
        boolean succeed = false;
        if(tClass == null || sql == null) return succeed;
        logger.debug("Execute update: " + sql);
        PreparedStatement stmt = null;
        Connection conn = obtainConnection();

        if(conn == null) return succeed;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            succeed = true;
        } catch (SQLException e) {
            logger.error("Mysql error", e);
        } finally {
            closeDBResource(stmt, conn, null);
        }
        return succeed;
    }

    public <T> long executeQueryForCount(Class<T> tClass, String countFieldName, String sql){
        if(tClass == null || sql == null) return 0;
        logger.debug("Execute query for count: " + sql);
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = obtainConnection();
        if(conn == null) return 0;

        long result = 0;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()) {
                try {
                    result = rs.getLong(countFieldName);
                } catch (Exception e) {
                    logger.error("Unexpected Error", e);
                }
            }
        } catch (SQLException e) {
            logger.error("Mysql error", e);
        } finally {
            closeDBResource(stmt, conn, rs);
        }

        return result;
    }

    private <T> T newObjectWithResultSet(Class<T> tClass, ResultSet rs){
        if(tClass == null) return null;
        Field[] fields = tClass.getDeclaredFields();
        Method[] methods = tClass.getDeclaredMethods();
        T t = null;
        try {
            t = tClass.getDeclaredConstructor().newInstance();
            for(Field field : fields) {
                Type fieldType = field.getGenericType();
                String fieldTypeName = fieldType.getTypeName();
                if(field.getDeclaredAnnotation(PoRequired.class) != null) {
                    for(Method method : methods) {
                        String methodName = method.getName();
                        if(methodName.startsWith("set") && methodName.substring(3).toLowerCase().equals(field.getName().toLowerCase())) {
                            if("java.lang.Integer".equals(fieldTypeName) || "int".equals(fieldTypeName)) {
                                method.invoke(t, rs.getInt(field.getName()));
                            } else if("java.lang.Long".equals(fieldTypeName) || "long".equals(fieldTypeName)){
                                method.invoke(t, rs.getLong(field.getName()));
                            } else if("java.lang.Float".equals(fieldTypeName) || "float".equals(fieldTypeName)) {
                                method.invoke(t, rs.getFloat(field.getName()));
                            } else if("java.lang.Double".equals(fieldTypeName) || "double".equals(fieldTypeName)){
                                method.invoke(t, rs.getDouble(field.getName()));
                            } else if("java.lang.Date".equals(fieldTypeName)) {
                                method.invoke(t, rs.getDate(fieldTypeName));
                            } else if("java.lang.Boolean".equals(fieldTypeName) || "boolean".equals(fieldTypeName)){
                                method.invoke(t, rs.getBoolean(field.getName()));
                            } else if(fieldType instanceof Class && ((Class<?>)fieldType).isEnum()){
                                Method fromValueMethod = ((Class<?>)fieldType).getDeclaredMethod("fromValue", String.class);
                                if(fromValueMethod != null) {
                                    method.invoke(t, fromValueMethod.invoke(null, rs.getString(field.getName())));
                                }
                            } else if("java.lang.String".equals(fieldTypeName)) {
                                method.invoke(t, rs.getString(field.getName()));
                            } else if("java.math.BigDecimal".equals(fieldTypeName)) {
                                method.invoke(t, rs.getBigDecimal(fieldTypeName));
                            }
                            break;
                        }
                    }
                }
            }
        } catch(Exception e){
            logger.error("Unexpected Error", e);
        }
        return t;
    }

    public <T> List<T> executeQuery(Class<T> tClass, String sql) {
        if(tClass == null || sql == null) return null;
        logger.debug("Execute query: " + sql);
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = obtainConnection();
        if(conn == null) return null;

        ArrayList<T> result = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()) {
                try {
                    result.add(newObjectWithResultSet(tClass, rs));
                } catch (Exception e) {
                    logger.error("Unexpected Error", e);
                }
            }
        } catch (SQLException e) {
            logger.error("Mysql error", e);
        } finally {
            closeDBResource(stmt, conn, rs);
        }

        return result;

    }

    public <T> T find(Class<T> tClass, String id) {
        if(tClass == null) return null;
        Field[] fields = tClass.getDeclaredFields();
        T t = null;
        String idFieldName = null, tableName = tClass.getSimpleName().toLowerCase();
        for(Field field : fields) {
            if(field.getDeclaredAnnotation(ID.class) != null) {
                idFieldName = field.getName();
                break;
            }
        }
        if(idFieldName == null) return null;

        List<T> result = executeQuery(tClass, "select * from " + tableName + " where " + idFieldName + "=" + id);

        if(result == null || result.size() == 0) {
            return null;
        }

        return result.get(0);

    }

    public static void main(String[] args){
        MysqlManager.loadDriver();
        MysqlManager mysqlManager = new MysqlManager();
        Client client = mysqlManager.find(Client.class, "1");
        System.out.println(client.getClientId());
        System.out.println(client.getName());

        mysqlManager.update(Client.class, "1", Map.of("name", "abc1"));

        client = mysqlManager.find(Client.class, "1");
        System.out.println(client.getClientId());
        System.out.println(client.getName());

        client = mysqlManager.findAll(Client.class, Filter.eq("clientId", "1")).get(0);
        System.out.println(client.getClientId());
        System.out.println(client.getName());

        client = mysqlManager.findAll(Client.class, Filter.and(Filter.eq("clientId", "1"), Filter.eq("name", "abc1"))).get(0);
        System.out.println(client.getClientId());
        System.out.println(client.getName());

        System.out.println(mysqlManager.executeQueryForCount(Client.class, "count", "select count(*) as count from client"));
    }
}
