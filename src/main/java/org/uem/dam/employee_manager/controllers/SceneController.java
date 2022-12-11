package org.uem.dam.employee_manager.controllers;

import org.uem.dam.employee_manager.MainApplication;
import org.uem.dam.employee_manager.SceneHelper;
import org.uem.dam.employee_manager.persistence.DBHelper;

public abstract class SceneController {
    private MainApplication mainApplication;

    public void setMainApplication(MainApplication mainApplication) {
        if (this.mainApplication != null) {
            throw new RuntimeException("Trying to set mainApplication, but it has been already defined");
        }
        this.mainApplication = mainApplication;
        if (this instanceof InitializableController) {
            ((InitializableController) this).onControllerLoaded();
        }
    }

    protected final SceneHelper getSceneHelper() {
        return mainApplication.getSceneHelper();
    }

    protected final DBHelper getDbHelper() {
        return mainApplication.getDbHelper();
    }
}