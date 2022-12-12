package org.uem.dam.employee_manager.controllers;

@FunctionalInterface
public interface RearrangableScene<T extends SceneController> {
    void changeState(T controller);
}
