package org.uem.dam.employee_manager.helpers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.uem.dam.employee_manager.AppInfo;
import org.uem.dam.employee_manager.MainApplication;
import org.uem.dam.employee_manager.controllers.FormDialog;
import org.uem.dam.employee_manager.controllers.RootScene;
import org.uem.dam.employee_manager.controllers.SceneController;
import org.uem.dam.employee_manager.enums.SceneReference;

import java.io.IOException;
import java.util.Optional;

public class SceneHelper {
    private final MainApplication mainApplication;
    private Parent rootNode;
    private BorderPane rootChildSceneNode;
    private RootScene rootController;
    private Scene scene;

    public SceneHelper(MainApplication mainApplication, Stage stage) throws IOException {
        this.mainApplication = mainApplication;
        initRootScene(stage);
        changeRootScene(SceneReference.SCENE_LOGIN);
    }

    @NotNull
    public static FXMLLoader generateFXMLoader(SceneReference sceneRes) {
        return new FXMLLoader(MainApplication.class.getResource(sceneRes.getScenePath()));
    }

    @NotNull
    public static Optional<ButtonType> promptAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(content);
        Optional<ButtonType> result = alert.showAndWait();
        return result;
    }

    public RootScene getRootController() {
        return rootController;
    }

    public void changeRootScene(Node node) {
        rootChildSceneNode.setCenter(node);
    }

    public void changeRootScene(SceneReference scene) {
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

    public Optional promptFormDialogScene(SceneReference dialogPaneScene, String title, Dialog dialog) {
        try {
            FXMLLoader loader = generateFXMLoader(dialogPaneScene);
            DialogPane dialogPane = (DialogPane) loadScene(loader);
            FormDialog formDialog = loader.getController();
            dialog.setDialogPane(dialogPane);
            dialog.setTitle(title);
            dialog.setResultConverter(formDialog.getResultCallback());
            return dialog.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassCastException e) {
            System.err.printf("%s dialog controller doesn't inherit FormDialog\n", dialogPaneScene);
        }
        return null;
    }

    public void promptDialogScene(SceneReference dialogPaneScene, String title, Dialog dialog) {
        // make dialog with loadScene method
        try {
            DialogPane dialogPane = (DialogPane) loadScene(dialogPaneScene);
            dialog.setDialogPane(dialogPane);
            dialog.setTitle(title);
            dialog.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Node loadScene(@NotNull FXMLLoader loader) throws IOException {
        Node sceneRootNode = loader.load();
        if (loader.getController() != null) {
            ((SceneController) loader.getController()).setMainApplication(mainApplication);
        } else
            System.out.println("This scene doesn't support user interaction");
        return sceneRootNode;
    }

    public Node loadScene(@NotNull SceneReference sceneRes) throws IOException {
        FXMLLoader loader = generateFXMLoader(sceneRes);
        return loadScene(loader);
    }

    private void initRootScene(@NotNull Stage primaryStage) throws IOException {
        // instantiate root node
        FXMLLoader loader = generateFXMLoader(SceneReference.SCENE_ROOT);
        rootNode = (Parent) loadScene(loader);
        rootController = loader.getController();
        rootChildSceneNode = rootController.rootChildScenePane;
        // init main window
        scene = new Scene(rootNode, AppInfo.APP_SIZE[0], AppInfo.APP_SIZE[1]);
        primaryStage.setTitle(AppInfo.APP_NAME);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
