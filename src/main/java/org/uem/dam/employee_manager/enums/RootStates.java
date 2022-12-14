package org.uem.dam.employee_manager.enums;

import org.uem.dam.employee_manager.controllers.RearrangableScene;
import org.uem.dam.employee_manager.controllers.RootScene;

public enum RootStates {
    STATE_LOCKED(root -> {
        root.dbMenu.setVisible(false);
        root.editMenu.setVisible(false);
        root.helpMenu.setVisible(true);
    }),
    STATE_LOGGED(root -> {
        root.dbMenu.setVisible(true);
        root.editMenu.setVisible(false);
        root.helpMenu.setVisible(true);
    }),
    STATE_MANAGING(root -> {
        root.dbMenu.setVisible(true);
        root.editMenu.setVisible(true);
        root.helpMenu.setVisible(true);
    });

    private final RearrangableScene<RootScene> rearrangableScene;

    RootStates(RearrangableScene<RootScene> rearrangableScene) {
        this.rearrangableScene = rearrangableScene;
    }

    public RearrangableScene<RootScene> getRearrangableScene() {
        return rearrangableScene;
    }
}
