package org.uem.dam.employee_manager.persistence;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
FIXME untested class. Functionality may be broken
 */
public class DBConnection {
    String dbname;
    String username;
    String password;

    public DBConnection() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(String.format("jdbc:mysql://localhost/%s?user=%s&password=%s",
                dbname, username, password));
    }

    private void setupSQLConfig() {
    }
}
