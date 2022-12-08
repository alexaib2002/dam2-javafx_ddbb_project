package org.uem.dam.employee_manager.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginScene extends SceneController {
    public TextField usernameTextField;
    public PasswordField passwordTextField;

    @Override
    protected void onControllerLoaded() {

    }


    public void onLogInAction(ActionEvent actionEvent) {
        System.err.println("Login action not implemented yet");
    }
}
