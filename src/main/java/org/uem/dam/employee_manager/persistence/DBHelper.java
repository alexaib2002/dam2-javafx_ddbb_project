package org.uem.dam.employee_manager.persistence;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class DBHelper {
    private DBPersistence dbPersistence;

    public DBPersistence getDbPersistence() {
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
