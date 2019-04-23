package com.youxiunanren.yxnr.db;

import com.youxiunanren.yxnr.db.mysql.MysqlManager;

public class DBManager {

    public static void init(){
        MysqlManager.loadDriver();
    }
}
