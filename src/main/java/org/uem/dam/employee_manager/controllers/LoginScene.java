package org.uem.dam.employee_manager.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class LoginScene extends SceneController {
    public TextField usernameTextField;
    public PasswordField passwordTextField;

    @Override
    protected void onControllerLoaded() {

    }


    public void onLogInAction(ActionEvent actionEvent) {
        try {
            startDBPersistence("employees", usernameTextField.getText(), passwordTextField.getText());
            requestSceneChange("scene-welcome.fxml");
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.show();
        }
    }
}
