package com.youxiunanren.yxnr.db.mysql;

import com.youxiunanren.yxnr.model.annotation.ID;
import com.youxiunanren.yxnr.model.annotation.PoRequired;
import com.youxiunanren.yxnr.modules.authentication.models.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;

public class MysqlManager {
    private static Logger logger = LoggerFactory.getLogger(MysqlManager.class);

    public static void loadDriver(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            logger.error("Failed to load mysql driver", e);
        }
    }

    public static Connection obtainConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yxnr_auth?user=root&password=rat.aid.two-666");
        } catch (SQLException e) {
            logger.error("Failed to obtain mysql connection", e);
        }
        return conn;
    }

    public static <T> T get(Class<T> tClass, String id) {
        if(tClass == null) return null;
        Field[] fields = tClass.getDeclaredFields();
        Method[] methods = tClass.getDeclaredMethods();
        T t = null;
        String idFieldName = null, tableName = tClass.getSimpleName().toLowerCase();
        for(Field field : fields) {
            if(field.getDeclaredAnnotation(ID.class) != null) {
                idFieldName = field.getName();
                break;
            }
        }
        if(idFieldName == null) return null;
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = obtainConnection();
        if(conn == null) return null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from " + tableName + " where " + idFieldName + "=" + id);

            if(rs.next()) {
                try {
                    t = tClass.getDeclaredConstructor().newInstance();
                    for(Field field : fields) {
                        if(field.getDeclaredAnnotation(PoRequired.class) != null) {
                            for(Method method : methods) {
                                String methodName = method.getName();
                                if(methodName.startsWith("set") && methodName.substring(3).toLowerCase().equals(field.getName().toLowerCase())) {
                                    method.invoke(t, rs.getString(field.getName()));
                                    break;
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    logger.error("Unexpected Error", e);
                }
            }
        } catch (SQLException e) {
            logger.error("Mysql error", e);
        } finally {
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
        }

        return t;

    }

    public static void main(String[] args){
        MysqlManager.loadDriver();
        Client client = MysqlManager.get(Client.class, "1");
        System.out.println(client.getClientId());
        System.out.println(client.getName());
    }
}
