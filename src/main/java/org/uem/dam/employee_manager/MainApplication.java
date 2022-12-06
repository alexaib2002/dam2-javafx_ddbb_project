package org.uem.dam.employee_manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.uem.dam.employee_manager.controllers.SceneController;

import java.io.IOException;

public class MainApplication extends Application {

    private VBox rootNode;
    private Scene scene;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        initRootScene(stage);
        changeRootScene("welcome-scene.fxml");
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
            errorAlert.showAndWait();
        } catch (IllegalStateException e) {
            System.err.println(String.format("%s IllegalState with %s argument", e.getMessage(), scene));
        }
    }

    private Node loadScene(String sceneRes) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource(sceneRes));
        Node sceneRootNode = loader.load();
        ((SceneController) loader.getController()).setMainApplication(this);
        return sceneRootNode;
    }

    private void initRootScene(Stage primaryStage) throws IOException {
        // instantiate root node
        rootNode = (VBox) loadScene("root-scene.fxml");
        // init main window
        scene = new Scene(rootNode, AppInfo.APP_SIZE[0], AppInfo.APP_SIZE[1]);
        primaryStage.setTitle(AppInfo.APP_NAME);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}