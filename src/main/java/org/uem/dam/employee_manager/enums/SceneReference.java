package org.uem.dam.employee_manager.enums;

public enum SceneReference {
    // based on the resources of org.uem.dam.employee_manager fxml files
    DIALOG_ABOUT("fxml/dialog-about.fxml"),
    DIALOG_USERADD("fxml/dialog-useradd.fxml"),
    DIALOG_USERFIND("fxml/dialog-userfind.fxml"),
    DIALOG_USERUPDATE("fxml/dialog-userupdate.fxml"),
    SCENE_LOGIN("fxml/scene-login.fxml"),
    SCENE_MANAGEMENT("fxml/scene-management.fxml"),
    SCENE_ROOT("fxml/scene-root.fxml"),
    SCENE_WELCOME("fxml/scene-welcome.fxml");

    private final String scenePath;

    SceneReference(String scenePath) {
        this.scenePath = scenePath;
    }

    public String getScenePath() {
        return scenePath;
    }
}
