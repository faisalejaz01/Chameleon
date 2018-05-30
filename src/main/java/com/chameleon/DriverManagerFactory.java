package com.chameleon;

import com.chameleon.web.driverManager.ChromeDriverManager;
import com.chameleon.web.driverManager.EdgeDriverManager;
import com.chameleon.web.driverManager.FirefoxDriverManager;
import com.chameleon.web.driverManager.InternetExplorerDriverManager;
import com.chameleon.web.driverManager.SafariDriverManager;

public class DriverManagerFactory {

    public static DriverManager getManager(DriverType type) {

        DriverManager driverManager;

        switch (type) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
            case FIREFOX:
            case HTML:
                driverManager = new FirefoxDriverManager();
                break;
            case EDGE:
                driverManager = new EdgeDriverManager();
                break;
            case INTERNETEXPLORER:
                driverManager = new InternetExplorerDriverManager();
                break;
            default:
                driverManager = new SafariDriverManager();
                break;
        }
        return driverManager;

    }

    public static DriverManager getManager(DriverType type, DriverOptionsManager options) {

        DriverManager driverManager;

        switch (type) {
            case CHROME:
                driverManager = new ChromeDriverManager(options.getChromeOptions());
                break;
            case FIREFOX:
            case HTML:
                driverManager = new FirefoxDriverManager(options.getFirefoxOptions());
                break;
            case EDGE:
                driverManager = new EdgeDriverManager(options.getEdgeOptions());
                break;
            case INTERNETEXPLORER:
                driverManager = new InternetExplorerDriverManager(options.getInternetExplorerOptions());
                break;
            default:
                driverManager = new SafariDriverManager(options.getSafariOptions());
                break;
        }
        return driverManager;

    }
}