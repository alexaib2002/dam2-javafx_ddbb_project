package org.uem.dam.employee_manager.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import org.uem.dam.employee_manager.enums.EmployeeContract;
import org.uem.dam.employee_manager.helpers.WriterHelper;
import org.uem.dam.employee_manager.javabeans.Employee;

import java.sql.SQLException;

public class ManagementScene extends SceneController implements InitializableController {

    public static final float COLUMN_WIDTH = 100f;
    @FXML
    TextArea logTextArea;
    @FXML
    ScrollPane contentTableScrollPane;
    @FXML
    TableView<Employee> dataTableView;

    private final ObservableList<Employee> dataEmployees = FXCollections.observableArrayList();

    public void onControllerLoaded() {
        initDataTableView();
        dataTableView.setItems(dataEmployees);
        updateEmployees();
        WriterHelper.setLogArea(logTextArea);
        getSceneHelper().getRootController().setTableUpdateCallback(this::updateEmployees);
    }

    private void initDataTableView() {
        TableColumn tableColumn;
        for (EmployeeContract employeeContract: EmployeeContract.values()
             ) {
            tableColumn = new TableColumn<>(employeeContract.name());
            tableColumn.setCellValueFactory(employeeContract.getColumnCellFactory());
            tableColumn.setPrefWidth(COLUMN_WIDTH);
            dataTableView.getColumns().add(tableColumn);
        }
    }

    // TODO should be called after every change in the database
    private void updateEmployees() {
        dataEmployees.clear();
        try {
            dataEmployees.addAll(getDbHelper().getDbPersistence().queryEmployees());
        } catch (SQLException e) {
            WriterHelper.write(e.getMessage());
        }
    }
}
