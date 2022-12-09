package org.uem.dam.employee_manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.uem.dam.employee_manager.controllers.SceneController;
import org.uem.dam.employee_manager.persistence.DBConnection;
import org.uem.dam.employee_manager.persistence.DBPersistence;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class MainApplication extends Application {

    private VBox rootNode;
    private Scene scene;
    private DBPersistence dbPersistence;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        initRootScene(stage);
        initDBPersistence();
        changeRootScene("scene-login.fxml");
    }

    public Scene getScene() {
        return scene;
    }

    public void changeRootScene(Node node) {
        BorderPane rootChildScenePane = (BorderPane) rootNode.lookup("#rootChildScenePane");
        rootChildScenePane.setCenter(node);
    }

    public void changeRootScene(String scene) {
        try {
            changeRootScene(loadScene(scene));
        } catch (IOException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Couldn't update root scene");
            errorAlert.setHeaderText("Error while updating app content");
            errorAlert.setContentText(String.format("Cause: %s\non %s", e.getCause(), e.getMessage()));
            errorAlert.showAndWait();
        } catch (IllegalStateException e) {
            System.err.println(String.format("%s IllegalState with %s argument", e.getMessage(), scene));
        }
    }

    public void popupDialogScene(String scene, String title, String header) {
        try {
            DialogPane dialogPane = (DialogPane) loadScene(scene);
            Dialog dialog = new Dialog();
            dialog.setResizable(true);
            dialog.setDialogPane(dialogPane);
            dialog.setTitle(title);
            dialog.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Node loadScene(String sceneRes) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource(sceneRes));
        Node sceneRootNode = loader.load();
        ((SceneController) loader.getController()).setMainApplication(this);
        return sceneRootNode;
    }

    private void initRootScene(@NotNull Stage primaryStage) throws IOException {
        // instantiate root node
        rootNode = (VBox) loadScene("scene-root.fxml");
        // init main window
        scene = new Scene(rootNode, AppInfo.APP_SIZE[0], AppInfo.APP_SIZE[1]);
        primaryStage.setTitle(AppInfo.APP_NAME);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initDBPersistence() {
        // should be triggered after login
        try {
            dbPersistence = new DBPersistence(new DBConnection("employees", "root", "1234"));
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        // FIXME enable for debug
//        try {
//            dbPersistence.getEmployees();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}