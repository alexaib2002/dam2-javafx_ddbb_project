package org.uem.dam.employee_manager.utils;

public abstract class SQLQueryBuilder {

    public static String buildSelectQuery(String table, String[] cols, String[] where, String orderBy,
                                          boolean distinct) {
        String query = "SELECT ";

        if (distinct) {
            query += "DISTINCT ";
        }

        int c = 0; // counter
        // append columns
        for (String col : cols) {
            query += col;
            if (c != cols.length - 1) {
                query += ", ";
            }
            c++;
        }
        c = 0;

        // append table
        query += String.format(" FROM %s", table);
        // where statement

        if (where != null) {
            if (where.length > 0) {
                for (String whereStmt : where) {
                    if (!whereStmt.isBlank() || !whereStmt.isEmpty()) {
                        if (c == 0) {
                            query += " WHERE ";
                        } else {
                            query += " AND ";
                        }
                        query += whereStmt;
                    }
                    c++;
                }
            }
        }

        if (orderBy != null) {
            query += String.format(" ORDER BY %s DESC", orderBy);
        }

        query += ";";

        return query;
    }

    public static String buildInsertQuery(String table, String[] cols) {

        if (table.isEmpty() || table.isBlank() || cols.length <= 0) {
            throw new IllegalArgumentException();
        }

        // initial statements
        String query = String.format("INSERT INTO %s ", table);

        // append columns
        int c = 0; // counter
        for (String col : cols) {
            if (c == 0) {
                query += "(";
            }

            query += String.format("\'%s\'", col);

            if (c == cols.length - 1) {
                query += ")";
            } else {
                query += ", ";
            }

            c++;
        }

        query += " VALUES ";

        for (int i = 0; i < cols.length; i++) {
            if (i == 0) {
                query += "(";
            }

            query += "?";

            if (i == cols.length - 1) {
                query += ")";
            } else {
                query += ", ";
            }
        }

        query += ";";

        return query;
    }

    public static String buildDeleteQuery(String table, String cond) {

        if (table.isEmpty() || table.isBlank() || cond.isBlank() || cond.isEmpty()) {
            throw new IllegalArgumentException();
        }

        // initial statements
        String query = String.format("DELETE FROM %s ", table);
        query += String.format("WHERE %s = ?;", cond);

        return query;
    }

    public static String buildUpdateQuery(String table, String[] cols, String where) {

        if (table.isEmpty() || table.isBlank() || cols.length <= 0) {
            throw new IllegalArgumentException();
        }

        // initial statements
        String query = String.format("UPDATE %s ", table);

        // append columns
        int c = 0; // counter
        for (String col : cols) {
            if (c == 0) {
                query += "SET ";
            }

            query += String.format("%s = ?", col);

            if (c == cols.length - 1) {
                query += " ";
            } else {
                query += ", ";
            }

            c++;
        }

        query += String.format("WHERE %s = ?;", where);

        return query;
    }

}
