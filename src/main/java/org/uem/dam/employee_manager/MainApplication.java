package org.uem.dam.employee_manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.uem.dam.employee_manager.controllers.SceneController;
import org.uem.dam.employee_manager.persistence.DBHelper;

import java.io.IOException;
import java.util.Optional;

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