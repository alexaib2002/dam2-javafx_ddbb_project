package org.uem.dam.employee_manager;

import javafx.application.Application;
import javafx.stage.Stage;
import org.uem.dam.employee_manager.helpers.DBHelper;
import org.uem.dam.employee_manager.helpers.SceneHelper;

import java.io.IOException;

public class MainApplication extends Application {
    private final DBHelper dbHelper = new DBHelper();
    private SceneHelper sceneHelper;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        sceneHelper = new SceneHelper(this, stage);
    }

    public DBHelper getDbHelper() {
        return dbHelper;
    }

    public SceneHelper getSceneHelper() {
        return sceneHelper;
    }
}