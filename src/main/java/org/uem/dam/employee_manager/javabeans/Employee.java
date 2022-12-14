package org.uem.dam.employee_manager.javabeans;

public record Employee(
        int employeeNo,
        String deptNo,
        String birthDate,
        String firstName,
        String lastName,
        String gender,
        String hireDate
) {
    public Employee(int employeeNo, String deptNo) {
        this(employeeNo, deptNo, null, null, null, null, null);
    }
}
