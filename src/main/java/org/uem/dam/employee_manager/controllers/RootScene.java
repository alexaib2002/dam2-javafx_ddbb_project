package org.uem.dam.employee_manager.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class RootScene extends SceneController {
    @FXML
    public MenuItem logOutMenuItem;
    @FXML
    public MenuItem addMenuItem;
    @FXML
    public MenuItem deleteMenuItem;
    @FXML
    public MenuItem findMenuItem;
    @FXML
    public MenuItem aboutMenuItem;
    @FXML
    public BorderPane rootChildScenePane;

    public void onLogOutMenuAction(ActionEvent actionEvent) {
        requestSceneChange("scene-login.fxml");
        getDbHelper().endDBPersistence();
    }

    public void onAddMenuAction(ActionEvent actionEvent) {
        requestPopupDialogScene("dialog-useradd.fxml", "Add new employee", "New employee data");
    }

    public void onDeleteMenuAction(ActionEvent actionEvent) {
        System.err.println("user deletion not implemented yet");
    }

    public void onFindMenuAction(ActionEvent actionEvent) {
        requestPopupDialogScene("dialog-userfind.fxml", "Find employee", "Employee data");
    }

    public void onAboutMenuAction(ActionEvent actionEvent) {
        System.err.println("about info not implemented yet");
    }
}
