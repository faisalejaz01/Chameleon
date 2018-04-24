package com.salesforce.api.rest.processor;

import com.orasi.api.restServices.RestResponse;
import com.salesforce.api.domain.Account;
import com.salesforce.api.rest.SalesforceRest;

public class AccountProcessor extends SalesforceRest {
    private String resource = "Account/";

    public RestResponse createAccount(Account account) {
        return sendPostRequest(resource, account);
    }

    public RestResponse getAccount(Account account) {
        return getAccount(account.getId());
    }

    public RestResponse getAccount(String id) {
        return sendGetRequest(resource + id);
    }

    public RestResponse deleteAccount(Account account) {
        return deleteAccount(account.getId());
    }

    public RestResponse deleteAccount(String id) {
        return sendDeleteRequest(resource + id);
    }
}
