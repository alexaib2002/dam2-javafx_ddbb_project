package org.uem.dam.employee_manager.controllers;

import javafx.scene.control.*;
import javafx.util.Callback;
import net.synedra.validatorfx.Validator;
import org.uem.dam.employee_manager.javabeans.Employee;

public class UseraddDialog extends FormDialog<Employee> {

    public Spinner<Integer> empNoInput;
    public TextField deptNoInput;
    public DatePicker birthDateInput;
    public TextField firstNameInput;
    public TextField lastNameInput;
    public ToggleGroup genderButtons;
    public DatePicker hireDateInput;

    @Override
    public void initValidator(Validator validator) {
        validator.createCheck()
                .dependsOn("empNo", empNoInput.valueProperty())
                .withMethod((context) -> {
                    Integer empNo = context.get("empNo");
                    if (empNo == null || empNo < 0) {
                        context.error("Employee number cannot be empty or negative");
                    }
                })
                .decorates(empNoInput.getEditor());
        validator.createCheck()
                .dependsOn("deptNo", deptNoInput.textProperty())
                .withMethod((context) -> {
                    String deptNo = context.get("deptNo");
                    if (deptNo == null || deptNo.length() != 4) {
                        context.error("Department number cannot be empty and must be 4 characters long");
                    }
                })
                .decorates(deptNoInput);
        validator.createCheck()
                .dependsOn("firstName", firstNameInput.textProperty())
                .withMethod((context) -> {
                    String firstName = context.get("firstName");
                    if (firstName == null || firstName.isEmpty()) {
                        context.error("First name cannot be empty");
                    }
                })
                .decorates(firstNameInput);
        validator.createCheck()
                .dependsOn("lastName", lastNameInput.textProperty())
                .withMethod((context) -> {
                    String lastName = context.get("lastName");
                    if (lastName == null || lastName.isEmpty()) {
                        context.error("Last name cannot be empty");
                    }
                })
                .decorates(lastNameInput);
    }

    @Override
    public Callback<ButtonType, Employee> initResultCallback() {
        return (ButtonType buttonType) -> {
            if (buttonType == ButtonType.FINISH) {
                Employee employee = getEmployee();
                if (employee != null)
                    return employee;
            }
            return null;
        };
    }

    private Employee getEmployee() {
        if (!validate())
            return null;
        return new Employee(empNoInput.getValue(), deptNoInput.getText(), birthDateInput.getValue().toString(),
                firstNameInput.getText(), lastNameInput.getText(), ((RadioButton) genderButtons.getSelectedToggle())
                .getText().equals("Male") ? "M" : "F", hireDateInput.getValue().toString());
    }
}
