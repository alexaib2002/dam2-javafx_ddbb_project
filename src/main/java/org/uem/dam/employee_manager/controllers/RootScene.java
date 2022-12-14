package org.uem.dam.employee_manager.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import org.uem.dam.employee_manager.enums.RootStates;
import org.uem.dam.employee_manager.enums.SceneReference;
import org.uem.dam.employee_manager.helpers.SceneHelper;
import org.uem.dam.employee_manager.helpers.WriterHelper;
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

    private Runnable tableUpdateCallback;

    @Override
    public void onControllerLoaded() {
        setRootState(rootState);
    }

    public void setRootState(RootStates rootState) {
        this.rootState = rootState;
        rootState.getRearrangableScene().changeState(this);
    }

    public void setTableUpdateCallback(Runnable tableUpdateCallback) {
        this.tableUpdateCallback = tableUpdateCallback;
    }

    public void onLogOutMenuAction(ActionEvent actionEvent) {
        getSceneHelper().changeRootScene(SceneReference.SCENE_LOGIN);
        setRootState(RootStates.STATE_LOCKED);
        getDbHelper().endDBPersistence();
    }

    public void onAddMenuAction(ActionEvent actionEvent) {
        Optional<Employee> result = getSceneHelper()
                .promptFormDialogScene(SceneReference.DIALOG_USERADD, "Add Employee", new Dialog<Employee>());
        if (result.isPresent()) {
            try {
                getDbHelper().getDbPersistence().addEmployee(result.get());
            } catch (SQLException e) {
                WriterHelper.write(e.getMessage());
            }
        }
        tableUpdateCallback.run();
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
            SceneHelper.promptAlert("Error", "No employee selected", Alert.AlertType.ERROR);
        } catch (SQLException e) {
            WriterHelper.write(e.getMessage());
        }
        tableUpdateCallback.run();
    }

    public void onFindMenuAction(ActionEvent actionEvent) {
        TableView<Employee> tableView = (TableView<Employee>) getSceneHelper().getRootController().rootChildScenePane
                .getCenter().lookup("#dataTableView");
        Optional<?> result = getSceneHelper()
                .promptFormDialogScene(SceneReference.DIALOG_USERFIND, "Find Employee", new Dialog<>());
        if (result.isPresent()) {
            tableView.getSelectionModel().select((Employee) result.get());
        } else {
            tableView.getSelectionModel().clearSelection();
            SceneHelper.promptAlert("Information", "No employee found with the given employee number",
                    Alert.AlertType.INFORMATION);
        }
    }

    public void onUpdateDepartmentMenuItem(ActionEvent actionEvent) {
        // update selected employee's department
        try {
            TableView<Employee> tableView = (TableView<Employee>) getSceneHelper().getRootController()
                    .rootChildScenePane.getCenter().lookup("#dataTableView");
            // get focused row
            Employee employee = tableView.getSelectionModel().getSelectedItem();
            Optional<String> result = getSceneHelper()
                    .promptFormDialogScene(SceneReference.DIALOG_USERUPDATE, "Update Employee Department",
                            new Dialog<>());
            if (result.isPresent()) {
                getDbHelper().getDbPersistence().updateEmployee(employee.employeeNo(), result.get());
            }
        } catch (NullPointerException e) {
            SceneHelper.promptAlert("Error", "No employee selected", Alert.AlertType.ERROR);
        } catch (SQLException e) {
            WriterHelper.write(e.getMessage());
        }
        tableUpdateCallback.run();
    }

    public void onAboutMenuAction(ActionEvent actionEvent) {
        getSceneHelper().promptDialogScene(SceneReference.DIALOG_ABOUT, "About", new Dialog<>());
    }
}
