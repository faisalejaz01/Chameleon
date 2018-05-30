package com.chameleon;

public enum DriverType {
    CHROME("chrome", "ch"),
    FIREFOX("firefox", "ff"),
    INTERNETEXPLORER("internetexplorer", "ie", "iexplorer"),
    SAFARI("safari"),
    EDGE("edge", "microsoftedge"),
    ANDROID("android"),
    IOS("ios"),
    APPIUM("appium"),
    HTML("html");

    private final String[] type;

    DriverType(String... type) {
        this.type = type;
    }

    public String[] getDriverType() {
        return type;
    }

    public static DriverType fromString(String type) {
        for (DriverType driverType : values()) {
            if (driverType.toString().equalsIgnoreCase(type)) {
                return driverType;
            }
        }

        for (DriverType browser : DriverType.values()) {
            for (String driverType : browser.getDriverType()) {
                if (driverType.equalsIgnoreCase(type)) {
                    return browser;
                }
            }
        }

        throw new AutomationException("No DriverType defined found for requested value [ " + type + " ]");
    }
}