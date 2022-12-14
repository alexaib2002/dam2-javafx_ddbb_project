package org.uem.dam.employee_manager.controllers;

import javafx.scene.control.Label;
import org.uem.dam.employee_manager.enums.RootStates;
import org.uem.dam.employee_manager.enums.SceneReference;

public class WelcomeScene extends SceneController implements InitializableController {
    public Label welcomeLabel;

    @Override
    public void onControllerLoaded() {
        welcomeLabel.setText(String.format(welcomeLabel.getText(), getDbHelper().getDbPersistence()
                .getDbConnection().getUsername()));
    }

    public void onActionStartButton() {
        getSceneHelper().changeRootScene(SceneReference.SCENE_MANAGEMENT);
        getSceneHelper().getRootController().setRootState(RootStates.STATE_MANAGING);
    }
}
