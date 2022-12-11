package org.uem.dam.employee_manager.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.util.Callback;
import net.synedra.validatorfx.Validator;

public abstract class FormDialog<T> extends SceneController implements InitializableController {

    protected final Callback<ButtonType, T> resultCallback;
    private final Validator validator = new Validator();
    @FXML
    public DialogPane dialogPane;

    protected FormDialog() {
        resultCallback = initResultCallback();
    }

    public abstract void initValidator(Validator validator);

    public abstract Callback<ButtonType, T> initResultCallback();

    @Override
    public void onControllerLoaded() {
        if (dialogPane == null) {
            System.err.printf("%s doesn't hava an fx:id pointing to its dialogPane. " +
                    "Expect breakages from now on", getClass().getSimpleName());
            return;
        }
        dialogPane.lookupButton(ButtonType.FINISH).addEventFilter(ActionEvent.ACTION, event -> {
            if (!validate())
                event.consume();
        });
        initValidator(validator);
    }

    public Callback<ButtonType, T> getResultCallback() {
        return resultCallback;
    }

    public boolean validate() {
        return validator.validate();
    }

}
