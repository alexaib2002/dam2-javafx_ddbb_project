package org.uem.dam.employee_manager;

import java.util.HashMap;

public abstract class AppInfo {
    public static final String APP_NAME = "Employee Manager";
    public static final String APP_VERSION = "devel";
    public static final int[] APP_SIZE = {800, 600};
    public static final HashMap<String, String> APP_ATTRIBUTIONS = new HashMap<>() {{
        put("App icon", "https://www.flaticon.com");
    }};
}
