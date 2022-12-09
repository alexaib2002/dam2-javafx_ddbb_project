package org.uem.dam.employee_manager.persistence;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private String dbname;
    private String username;
    private String password;

    public DBConnection(String dbname, String username, String password) throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        this.dbname = dbname;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        // FIXME setup proper connection with docker
        System.out.println("(Warning) Unsafe connection to database");
        //localhost:3306
        return DriverManager.getConnection(String.format("jdbc:mysql://192.168.122.90:30306/%s?user=%s&password=%s",
                dbname, username, password));
    }
}
