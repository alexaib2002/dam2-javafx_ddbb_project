package org.uem.dam.employee_manager.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import net.synedra.validatorfx.Validator;

public class UserupdateDialog extends FormDialog<String> {
    @FXML
    TextField depTextField;

    @Override
    public void initValidator(Validator validator) {
        validator.createCheck()
                .dependsOn("depNo", depTextField.textProperty())
                .withMethod((context) -> {
                    String depNo = context.get("depNo");
                    if (depNo == null || depNo.length() != 4) {
                        context.error("Department number cannot be empty and must be 4 characters long");
                    }
                })
                .decorates(depTextField);
    }

    @Override
    public Callback<ButtonType, String> initResultCallback() {
        return (ButtonType buttonType) -> {
            if (buttonType == ButtonType.FINISH) {
                return depTextField.getText();
            }
            return null;
        };
    }
}
