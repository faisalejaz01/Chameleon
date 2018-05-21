package com.salesforce.api.rest.manager;

public class SalesforceObjectManager {
    private static ThreadLocal<SalesforceObjectManager> instance = new ThreadLocal<>();

    public static SalesforceObjectManager getInstance() {
        if (instance == null || instance.get() == null) {
            instance.set(new SalesforceObjectManager());
        }
        return instance.get();
    }

    public AccountsManager accounts() {
        return AccountsManager.getInstance();
    }
}
