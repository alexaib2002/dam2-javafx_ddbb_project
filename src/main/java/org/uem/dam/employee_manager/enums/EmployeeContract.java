package org.uem.dam.employee_manager.enums;

import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import org.uem.dam.employee_manager.javabeans.Employee;

public enum EmployeeContract {
    EMPLOYEE_NO(
            employeeCellDataFeatures -> new ReadOnlyIntegerWrapper(employeeCellDataFeatures.getValue().employeeNo()).asObject()
    ),
    DEPT_NO(
            employeeCellDataFeatures -> new ReadOnlyStringWrapper(employeeCellDataFeatures.getValue().deptNo())
    ),
    BIRTH_DATE(
            employeeCellDataFeatures -> new ReadOnlyStringWrapper(employeeCellDataFeatures.getValue().birthDate())
    ),
    FIRST_NAME(
            employeeCellDataFeatures -> new ReadOnlyStringWrapper(employeeCellDataFeatures.getValue().firstName())
    ),
    LAST_NAME(
            employeeCellDataFeatures -> new ReadOnlyStringWrapper(employeeCellDataFeatures.getValue().lastName())
    ),
    GENDER(
            employeeCellDataFeatures -> new ReadOnlyBooleanWrapper(employeeCellDataFeatures.getValue().gender())
    ),
    HIRE_DATE(
            employeeCellDataFeatures -> new ReadOnlyStringWrapper(employeeCellDataFeatures.getValue().hireDate())
    );

    // contains lambda expression with record access wrapper
    private Callback<TableColumn.CellDataFeatures<Employee, ?>, ObservableValue<?>> columnCellFactory;

    EmployeeContract(Callback<TableColumn.CellDataFeatures<Employee, ?>, ObservableValue<?>> columnCellFactory) {
        this.columnCellFactory = columnCellFactory;
    }

    public Callback<TableColumn.CellDataFeatures<Employee, ?>, ObservableValue<?>> getColumnCellFactory() {
        return columnCellFactory;
    }
}
