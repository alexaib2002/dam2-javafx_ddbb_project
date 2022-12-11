package org.uem.dam.employee_manager.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import org.uem.dam.employee_manager.javabeans.Employee;

import java.sql.SQLException;
import java.util.Optional;

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
        getSceneHelper().changeRootScene("scene-login.fxml");
        getDbHelper().endDBPersistence();
    }

    public void onAddMenuAction(ActionEvent actionEvent) {
        // FIXME refactor popupDialogScene into its own class so we can use generics
        Optional<Employee> result = getSceneHelper()
                .popupDialogScene("dialog-useradd.fxml", "Add Employee", new Dialog<Employee>());
        if (result.isPresent()) {
            try {
                getDbHelper().getDbPersistence().addEmployee(result.get());
                System.out.println("Successfully added employee");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void onDeleteMenuAction(ActionEvent actionEvent) {
        System.err.println("user deletion not implemented yet");
    }

    public void onFindMenuAction(ActionEvent actionEvent) {
        Optional result = getSceneHelper()
                .popupDialogScene("dialog-userfind.fxml", "Find Employee", new Dialog());
    }

    public void onAboutMenuAction(ActionEvent actionEvent) {
        System.err.println("about info not implemented yet");
    }
}
