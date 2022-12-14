package org.uem.dam.employee_manager.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;
import javafx.util.Callback;
import net.synedra.validatorfx.Validator;
import org.uem.dam.employee_manager.helpers.WriterHelper;
import org.uem.dam.employee_manager.javabeans.Employee;

import java.sql.SQLException;

public class UserfindDialog extends FormDialog<Employee> {

    @FXML
    public Spinner<Integer> empNoInput;

    @Override
    public void initValidator(Validator validator) {

    }

    @Override
    public Callback<ButtonType, Employee> initResultCallback() {
        return (ButtonType buttonType) -> {
            if (buttonType == ButtonType.FINISH) {
                Employee employee = null;
                try {
                    employee = getDbHelper().getDbPersistence().searchEmployee(empNoInput.getValue());
                } catch (SQLException e) {
                    WriterHelper.write(e.getMessage());
                }
                if (employee != null)
                    return employee;
            }
            return null;
        };
    }
}
