package org.uem.dam.employee_manager.javabeans;

public record Employee(
        int employeeNo,
        String deptNo,
        String birthDate,
        String firstName,
        String lastName,
        boolean gender,
        String hireDate
) {
}
