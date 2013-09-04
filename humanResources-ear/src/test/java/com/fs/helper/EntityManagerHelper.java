package com.fs.helper;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;


public class EntityManagerHelper {

    public static EntityManagerFactory getEntityManagerFactory() {
        String databaseUrl = replaceIfNullOrEmtpy(System.getProperty("database.url"),"localhost:3307/codeExampleDB") ;
        String databaseUser = replaceIfNullOrEmtpy(System.getProperty("database.user"),"codeExample");
        String databasePassword = replaceIfNullOrEmtpy(System.getProperty("database.password"),"codeExample") ;

        return getEntityManagerFactory(databaseUrl,databaseUser,databasePassword);
    }

    private static EntityManagerFactory getEntityManagerFactory(String url, String user, String password) {
        Map<String, String> emProps = new HashMap<String, String>();
        emProps.put("hibernate.connection.url","jdbc:mysql://"+url);
        emProps.put("hibernate.connection.username",user);
        emProps.put("hibernate.connection.password",password);
        return Persistence.createEntityManagerFactory("integration-test", emProps);
    }

    private static String replaceIfNullOrEmtpy(String value, String defaultValue) {
        if(value == null || value.isEmpty()) {
            value = defaultValue;
        }

        return value;
    }
}
