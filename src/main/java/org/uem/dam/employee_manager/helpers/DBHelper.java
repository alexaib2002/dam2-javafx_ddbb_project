package org.uem.dam.employee_manager.helpers;

import org.uem.dam.employee_manager.persistence.DBConnection;
import org.uem.dam.employee_manager.persistence.DBPersistence;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class DBHelper {
    private DBPersistence dbPersistence;

    public DBPersistence getDbPersistence() {
        if (dbPersistence == null) {
            System.err.println("FATAL: DBPersistence is not initialized");
        }
        return dbPersistence;
    }

    public void startDBPersistence(String dbname, String username, String password) throws SQLException {
        try {
            // FIXME hardcoded host
            dbPersistence = new DBPersistence(new DBConnection("SatelliteP50C", 30306, dbname, username, password));
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void endDBPersistence() {
        dbPersistence = null;
    }
}
