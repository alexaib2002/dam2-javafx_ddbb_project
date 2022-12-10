package org.uem.dam.employee_manager.controllers;

import javafx.scene.control.Label;

public class WelcomeScene extends SceneController {
    public Label welcomeLabel;

//    @FXML
//    public WebView welcomeWebView;

    @Override
    protected void onControllerLoaded() {
        welcomeLabel.setText(String.format(welcomeLabel.getText(), getDbPersistence().getDbConnection().getUsername()));
    }

    public void onActionStartButton() {
        requestSceneChange("scene-management.fxml");
    }
}
