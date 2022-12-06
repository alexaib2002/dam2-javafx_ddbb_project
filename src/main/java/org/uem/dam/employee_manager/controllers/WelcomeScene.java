package org.uem.dam.employee_manager.controllers;

public class WelcomeScene extends SceneController {

    @Override
    protected void onControllerLoaded() {

    }

    public void onActionStartButton() {
        requestSceneChange("management-scene.fxml");
    }
}
