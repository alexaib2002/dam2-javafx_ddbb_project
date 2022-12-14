package org.uem.dam.employee_manager.helpers;

import javafx.scene.control.TextArea;

public class WriterHelper {
    private static TextArea logArea;

    public static void setLogArea(TextArea logArea) {
        WriterHelper.logArea = logArea;
    }

    public static void write(String text) {
        try {
            logArea.appendText(text + "\n");
        } catch (NullPointerException e) {
            System.err.println("LogArea is not initialized, ignoring write request");
        }
    }
}
