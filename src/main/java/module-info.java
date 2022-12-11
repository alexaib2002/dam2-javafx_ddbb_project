module org.uem.dam.employee_manager {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.web;
    requires java.sql;
    requires mysql.connector.j;
    requires org.jetbrains.annotations;

    opens org.uem.dam.employee_manager to javafx.fxml;
    exports org.uem.dam.employee_manager;
    exports org.uem.dam.employee_manager.controllers;
    exports org.uem.dam.employee_manager.javabeans;
    opens org.uem.dam.employee_manager.controllers to javafx.fxml;
}