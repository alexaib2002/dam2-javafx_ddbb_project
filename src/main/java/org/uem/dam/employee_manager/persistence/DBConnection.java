package org.uem.dam.employee_manager.persistence;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private String host;
    private int port;
    private String dbname;
    private String username;
    private String password;

    public DBConnection(String host, int port, String dbname, String username, String password) throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        this.host = host;
        this.port = port;
        this.dbname = dbname;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        System.out.println("(Warning) Unsafe connection to database");
        return DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s",
                host, port, dbname, username, password));
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getDbname() {
        return dbname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}