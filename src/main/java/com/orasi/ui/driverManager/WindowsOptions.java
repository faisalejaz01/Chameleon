package com.orasi.ui.driverManager;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.MutableCapabilities;

public class WindowsOptions extends MutableCapabilities {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String app;
    private String windowsDriverServerIp = "127.0.0.1";
    private String windowsDriverServerPort = "4723";

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        setCapability("app", app);
        this.app = app;
    }

    public String getWindowsDriverServerIp() {
        return windowsDriverServerIp;
    }

    public void setWindowsDriverServerIp(String windowsDriverServerIp) {
        this.windowsDriverServerIp = windowsDriverServerIp;
    }

    public String getWindowsDriverServerPort() {
        return windowsDriverServerPort;
    }

    public void setWindowsDriverServerPort(String windowsDriverServerPort) {
        this.windowsDriverServerPort = windowsDriverServerPort;
    }

    public URL getUrl() {
        try {
            return new URL("http://" + windowsDriverServerIp + ":" + windowsDriverServerPort);
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
