package org.uem.dam.employee_manager.controllers;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class WelcomeScene extends SceneController {

    @FXML
    public WebView welcomeWebView;

    @Override
    protected void onControllerLoaded() {
        WebEngine webEngine = welcomeWebView.getEngine();
        webEngine.load("https://github.com/alexaib2002/dam2-javafx_ddbb_project");
    }

    public void onActionStartButton() {
        requestSceneChange("scene-management.fxml");
    }
}
