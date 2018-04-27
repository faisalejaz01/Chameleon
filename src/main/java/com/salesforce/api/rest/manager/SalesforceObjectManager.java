package com.salesforce.api.rest.manager;

public class SalesforceObjectManager {
    private ThreadLocal<AccountsManager> accounts = new ThreadLocal<>();
    private static ThreadLocal<SalesforceObjectManager> instance = new ThreadLocal<>();

    public static SalesforceObjectManager getInstance() {
        if (instance == null || instance.get() == null) {
            instance.set(new SalesforceObjectManager());
        }
        return instance.get();
    }

    public SalesforceObjectManager() {
        accounts.set(new AccountsManager());
    }

    public AccountsManager accounts() {
        return accounts.get();
    }
}
