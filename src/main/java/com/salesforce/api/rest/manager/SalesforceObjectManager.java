package com.salesforce.api.rest.manager;

public class SalesforceObjectManager {
    private ThreadLocal<AccountsManager> accounts = new ThreadLocal<>();

    public SalesforceObjectManager() {
        accounts.set(AccountsManager.getInstance());
    }

    public AccountsManager accounts() {
        return accounts.get();
    }
}
