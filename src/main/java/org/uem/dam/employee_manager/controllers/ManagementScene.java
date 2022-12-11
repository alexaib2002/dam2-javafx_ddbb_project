package org.uem.dam.employee_manager.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.uem.dam.employee_manager.enums.EmployeeContract;
import org.uem.dam.employee_manager.javabeans.Employee;

import java.sql.SQLException;

public class ManagementScene extends SceneController implements InitializableController {

    public static final float COLUMN_WIDTH = 100f;
    @FXML
    ScrollPane contentTableScrollPane;
    @FXML
    TableView<Employee> dataTableView;

    private final ObservableList<Employee> dataEmployees = FXCollections.observableArrayList();

    public void onControllerLoaded() {
        // side panel
//        detailsTitledPane.minWidthProperty().bind(contentTableScrollPane.minHeightProperty());
//        detailsTitledPane.maxWidthProperty().bind(contentTableScrollPane.maxHeightProperty());
//        detailsTitledPane.prefWidthProperty().bind(contentTableScrollPane.prefHeightProperty());
        initDataTableView();
        dataTableView.setItems(dataEmployees);
        updateEmployees();
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
            throw new RuntimeException(e);
        }
    }
}
