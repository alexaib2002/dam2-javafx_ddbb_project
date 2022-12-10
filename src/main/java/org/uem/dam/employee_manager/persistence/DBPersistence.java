package org.uem.dam.employee_manager.persistence;

import org.uem.dam.employee_manager.javabeans.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBPersistence {
    private final DBConnection dbConnection;

    public DBPersistence(DBConnection dbConnection) throws SQLException {
        this.dbConnection = dbConnection;
        dbConnection.getConnection().createStatement().execute("USE employees;");
    }

    public DBConnection getDbConnection() {
        return dbConnection;
    }

    public ArrayList<Employee> queryEmployees() throws SQLException {
        ArrayList<Employee> employees = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = dbConnection.getConnection().createStatement();
            rs = stmt.executeQuery("SELECT dept_emp.emp_no,dept_no,e.birth_date,e.first_name,e.last_name,e.gender,e.hire_date\n" +
                    "FROM dept_emp\n" +
                    "JOIN employees e on dept_emp.emp_no = e.emp_no\n" +
                    "LIMIT 0,500;"); // FIXME variable limit query size
            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("emp_no"),
                        rs.getString("dept_no"),
                        rs.getDate("birth_date").toString(),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("gender"),
                        rs.getDate("hire_date").toString()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return employees;
    }

    // FIXME not tested yet
    public void removeEmployee(int employeeNo) throws SQLException {
        Statement stmt = null;
        try {
            stmt = dbConnection.getConnection().createStatement();
            stmt.executeUpdate(String.format("DELETE FROM employees.employees WHERE emp_no = %d;", employeeNo));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    // FIXME not tested yet
    public void addEmployee(Employee employee) throws SQLException {
        Statement stmt = null;
        try {
            stmt = dbConnection.getConnection().createStatement();
            stmt.executeUpdate(String.format("INSERT INTO employees.employees VALUES (%d, '%s', '%s', '%s', '%s', '%s', '%s');",
                    employee.employeeNo(),
                    employee.deptNo(),
                    employee.birthDate(),
                    employee.firstName(),
                    employee.lastName(),
                    employee.gender(),
                    employee.hireDate()
            ));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    // FIXME not tested yet
    public void updateEmployee(Employee employee) throws SQLException {
        Statement stmt = null;
        try {
            stmt = dbConnection.getConnection().createStatement();
            stmt.executeUpdate(String.format("UPDATE employees.employees SET dept_no = '%s', birth_date = '%s', first_name = '%s', last_name = '%s', gender = '%s', hire_date = '%s' WHERE emp_no = %d;",
                    employee.deptNo(),
                    employee.deptNo(),
                    employee.birthDate(),
                    employee.firstName(),
                    employee.lastName(),
                    employee.gender(),
                    employee.hireDate()
            ));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}
