package org.uem.dam.employee_manager.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import org.uem.dam.employee_manager.SceneHelper;
import org.uem.dam.employee_manager.enums.RootStates;
import org.uem.dam.employee_manager.enums.SceneReference;
import org.uem.dam.employee_manager.javabeans.Employee;

import java.sql.SQLException;
import java.util.Optional;

public class RootScene extends SceneController implements InitializableController {
    @FXML
    public Menu dbMenu;
    @FXML
    public Menu editMenu;
    @FXML
    public Menu helpMenu;
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

    private RootStates rootState = RootStates.STATE_LOCKED;

    public void setRootState(RootStates rootState) {
        this.rootState = rootState;
        rootState.getRearrangableScene().changeState(this);
    }

    @Override
    public void onControllerLoaded() {
        setRootState(rootState);
    }

    public void onLogOutMenuAction(ActionEvent actionEvent) {
        getSceneHelper().changeRootScene(SceneReference.SCENE_LOGIN);
        getDbHelper().endDBPersistence();
    }

    public void onAddMenuAction(ActionEvent actionEvent) {
        // FIXME refactor popupDialogScene into its own class so we can use generics
        Optional<Employee> result = getSceneHelper()
                .promptDialogScene(SceneReference.DIALOG_USERADD, "Add Employee", new Dialog<Employee>());
        if (result.isPresent()) {
            try {
                getDbHelper().getDbPersistence().addEmployee(result.get());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void onDeleteMenuAction(ActionEvent actionEvent) {
        try {
            TableView<Employee> tableView = (TableView<Employee>) getSceneHelper().getRootController()
                    .rootChildScenePane.getCenter().lookup("#dataTableView");
            // get focused row
            Employee employee = tableView.getSelectionModel().getSelectedItem();
            Optional<ButtonType> result = SceneHelper.promptAlert("Delete Employee",
                    "Are you sure you want to delete employee " + employee.employeeNo() + "?",
                    Alert.AlertType.CONFIRMATION);
            if (result.get() == ButtonType.OK) {
                getDbHelper().getDbPersistence().removeEmployee(employee.employeeNo());
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No employee selected");
            alert.setContentText("Please select an employee to delete");
            alert.showAndWait();
        } catch (SQLException e) {
            // TODO write to log
        }
    }

    public void onFindMenuAction(ActionEvent actionEvent) {
        TableView<Employee> tableView = (TableView<Employee>) getSceneHelper().getRootController().rootChildScenePane
                .getCenter().lookup("#dataTableView");
        Optional result = getSceneHelper()
                .promptDialogScene(SceneReference.DIALOG_USERFIND, "Find Employee", new Dialog());
        if (result.isPresent()) {
            tableView.getSelectionModel().select((Employee) result.get());
        } else {
            tableView.getSelectionModel().clearSelection();
            SceneHelper.promptAlert("Information", "No employee found with the given employee number",
                    Alert.AlertType.INFORMATION);
        }
    }

    public void onAboutMenuAction(ActionEvent actionEvent) {
        System.err.println("about info not implemented yet");
    }
}
