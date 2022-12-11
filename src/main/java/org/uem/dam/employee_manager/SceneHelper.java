package org.uem.dam.employee_manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.uem.dam.employee_manager.controllers.SceneController;

import javax.security.auth.callback.Callback;
import java.io.IOException;
import java.util.Optional;

public class SceneHelper {
    private final MainApplication mainApplication;
    private VBox rootNode;
    private Scene scene;

    public SceneHelper(MainApplication mainApplication, Stage stage) throws IOException {
        this.mainApplication = mainApplication;
        initRootScene(stage);
        changeRootScene("scene-login.fxml");
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

    public Optional<ButtonType> popupDialogScene(String scene, String title, String header, Callback dialogCallback) {
        try {
            DialogPane dialogPane = (DialogPane) loadScene(scene);
            Dialog dialog = new Dialog();
            dialog.setResizable(true);
            dialog.setDialogPane(dialogPane);
            dialog.setTitle(title);
            dialog.setResultConverter();
            return dialog.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Node loadScene(String sceneRes) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource(sceneRes));
        Node sceneRootNode = loader.load();
        ((SceneController) loader.getController()).setMainApplication(mainApplication);
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
}
