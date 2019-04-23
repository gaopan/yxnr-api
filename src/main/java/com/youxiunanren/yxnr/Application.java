package com.youxiunanren.yxnr;

import com.youxiunanren.yxnr.db.DBManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        // Initialize DB
        DBManager.init();
        // Launch application
        SpringApplication.run(Application.class, args);
    }

}
