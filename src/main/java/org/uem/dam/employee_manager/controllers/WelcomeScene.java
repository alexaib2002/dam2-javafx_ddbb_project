package org.uem.dam.employee_manager.controllers;

import javafx.scene.control.Label;

public class WelcomeScene extends SceneController implements InitializableController {
    public Label welcomeLabel;

//    @FXML
//    public WebView welcomeWebView;

    @Override
    public void onControllerLoaded() {
        welcomeLabel.setText(String.format(welcomeLabel.getText(), getDbHelper().getDbPersistence()
                .getDbConnection().getUsername()));
    }

    public void onActionStartButton() {
        getSceneHelper().changeRootScene("scene-management.fxml");
    }
}
