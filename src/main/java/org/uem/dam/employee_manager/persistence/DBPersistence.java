package org.uem.dam.employee_manager.persistence;

import org.jetbrains.annotations.NotNull;
import org.uem.dam.employee_manager.helpers.WriterHelper;
import org.uem.dam.employee_manager.javabeans.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBPersistence {
    private final DBConnection dbConnection;

    public DBPersistence(@NotNull DBConnection dbConnection) throws SQLException {
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
                    "JOIN employees e on dept_emp.emp_no = e.emp_no;");
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
            WriterHelper.write(e.getMessage());
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

    public void removeEmployee(int employeeNo) throws SQLException {
        Statement stmt = null;
        try {
            stmt = dbConnection.getConnection().createStatement();
            stmt.executeUpdate(String.format("DELETE FROM employees WHERE emp_no = %s;", employeeNo));
            WriterHelper.write(String.format("Employee %s removed", employeeNo));
        } catch (SQLException e) {
            WriterHelper.write(e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public Employee searchEmployee(Integer employeeNo) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        Employee employee = null;
        try {
            stmt = dbConnection.getConnection().createStatement();
            rs = stmt.executeQuery(String.format(
                    "SELECT dept_emp.emp_no,dept_no,e.birth_date,e.first_name,e.last_name,e.gender,e.hire_date " +
                            "FROM dept_emp " +
                            "JOIN employees e ON dept_emp.emp_no = e.emp_no " +
                            "WHERE e.emp_no = %s;", employeeNo));
            while (rs.next()) {
                if (employee == null)
                    employee = new Employee(
                            rs.getInt("emp_no"),
                            rs.getString("dept_no"),
                            rs.getDate("birth_date").toString(),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("gender"),
                            rs.getDate("hire_date").toString());
                else
                    System.err.println("More than one employee with same primary key!!");
            }
        } catch (SQLException e) {
            WriterHelper.write(e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return employee;
    }

    public void addEmployee(@NotNull Employee employee) throws SQLException {
        Statement stmt = null;
        try {
            stmt = dbConnection.getConnection().createStatement();
            stmt.executeUpdate(String.format("INSERT INTO employees.employees VALUES (%d, '%s', '%s', '%s', '%s', '%s');",
                    employee.employeeNo(),
                    employee.birthDate(),
                    employee.firstName(),
                    employee.lastName(),
                    employee.gender(),
                    employee.hireDate()
            ));
            stmt.executeUpdate(String.format("INSERT INTO employees.dept_emp VALUES (%d, '%s', '%s', '%s');",
                    employee.employeeNo(),
                    employee.deptNo(),
                    employee.hireDate(),
                    "9999-01-01"
            ));
            WriterHelper.write(String.format("Employee %s added", employee.employeeNo()));
        } catch (SQLException e) {
            WriterHelper.write(e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void updateEmployee(int employeeNo, String deptNo) throws SQLException {
        Statement stmt = null;
        try {
            System.out.println(String.format("UPDATE employees.dept_emp SET dept_no = '%s' WHERE emp_no = %d;", deptNo, employeeNo));
            stmt = dbConnection.getConnection().createStatement();
            stmt.executeUpdate(String.format("UPDATE dept_emp " +
                            "SET dept_no = '%s' " +
                            "WHERE emp_no = %s;",
                    deptNo,
                    employeeNo
            ));
            WriterHelper.write(String.format("Employee %s updated", employeeNo));
        } catch (SQLException e) {
            WriterHelper.write(e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}
