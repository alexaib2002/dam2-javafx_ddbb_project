package org.uem.dam.employee_manager.enums;

import org.uem.dam.employee_manager.controllers.RearrangableScene;
import org.uem.dam.employee_manager.controllers.RootScene;

public enum RootStates {
    STATE_WELCOME(root -> {
        System.out.println("Welcome state");
        root.dbMenu.setVisible(false);
        root.editMenu.setVisible(false);
        root.helpMenu.setVisible(true);
    }),
    STATE_LOGGED(root -> {
        System.out.println("Logged state");
        root.dbMenu.setVisible(true);
        root.editMenu.setVisible(false);
        root.helpMenu.setVisible(false);
    }),
    STATE_ON_MANAGER(root -> {
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
