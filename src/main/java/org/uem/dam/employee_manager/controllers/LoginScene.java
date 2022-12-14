package org.uem.dam.employee_manager.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.uem.dam.employee_manager.enums.RootStates;
import org.uem.dam.employee_manager.enums.SceneReference;

import java.sql.SQLException;

public class LoginScene extends SceneController {
    public TextField usernameTextField;
    public PasswordField passwordTextField;

    public void onLogInAction(ActionEvent actionEvent) {
        try {
            getDbHelper()
                    .startDBPersistence("employees", usernameTextField.getText(), passwordTextField.getText());
            getSceneHelper().changeRootScene(SceneReference.SCENE_WELCOME);
            getSceneHelper().getRootController().setRootState(RootStates.STATE_LOGGED);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.show();
        }
    }
}
