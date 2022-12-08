package org.uem.dam.employee_manager.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class RootScene extends SceneController {
    @FXML
    public MenuItem switchUserMenuItem;
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

    @Override
    protected void onControllerLoaded() {

    }

    public void onSwitchUserMenuItemAction(ActionEvent actionEvent) {
        System.err.println("switch not implemented");
    }

    public void onLogOutMenuItemAction(ActionEvent actionEvent) {
        System.err.println("logout not implemented");
    }

    public void onAddMenuItemAction(ActionEvent actionEvent) {
        requestPopupDialogScene("dialog-useradd.fxml", "Add new employee", "New employee data");
    }

    public void onDeleteMenuItemAction(ActionEvent actionEvent) {
        System.err.println("user deletion not implemented yet");
    }

    public void onFindMenuItemAction(ActionEvent actionEvent) {
        requestPopupDialogScene("dialog-userfind.fxml", "Find employee", "Employee data");
    }

    public void onAboutMenuItemAction(ActionEvent actionEvent) {
        System.err.println("about info not implemented yet");
    }
}
