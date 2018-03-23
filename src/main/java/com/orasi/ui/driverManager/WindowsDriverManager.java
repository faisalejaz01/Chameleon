package com.orasi.ui.driverManager;

import java.net.URL;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.orasi.AutomationException;
import com.orasi.DriverManager;
import com.orasi.DriverType;

import io.appium.java_client.windows.WindowsDriver;

public class WindowsDriverManager extends DriverManager {

    private WindowsOptions options = null;

    public WindowsDriverManager() {
        throw new AutomationException("Windows driver cannot be started without options. Create WindowsOptions and pass in through Driver Factory");
    }

    public WindowsDriverManager(WindowsOptions options) {
        this.options = options;
    }

    @Override
    public void startService() {
        // if (null == driverService.get()) {
        // try {
        // driverService.set(new AppiumServiceBuilder()
        // .usingAnyFreePort()
        // .withIPAddress(AppiumServiceBuilder.DEFAULT_LOCAL_IP_ADDRESS)
        // .build());
        // driverService.get().start();
        // } catch (Exception e) {
        // throw new WebException("Failed to start Chrome driver service", e);
        // }
        // }
    }

    @Override
    public void createDriver() {
        driver = new WindowsDriver(options.getUrl(), options);
    }

    @Override
    public void createDriver(URL url) {
        driver = new RemoteWebDriver(url, options);
    }

    @Override
    public DriverType getDriverType() {
        return DriverType.WINDOWS;
    }

}
