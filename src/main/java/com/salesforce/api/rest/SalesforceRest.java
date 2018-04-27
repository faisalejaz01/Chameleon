package com.salesforce.api.rest;

import static com.orasi.api.restServices.Headers.HeaderType.JSON;

import com.orasi.api.restServices.RestResponse;
import com.orasi.api.restServices.RestService;
import com.salesforce.api.rest.authentication.AuthZ;
import com.salesforce.api.rest.authentication.AuthZToken;

public class SalesforceRest {
    private static final String BASE_URL = "https://na59.salesforce.com/services/data/v42.0/sobjects/";
    private RestService rest = new RestService();
    private static AuthZToken token;

    protected RestResponse sendDeleteRequest(String resource) {
        authorize();
        return rest.sendDeleteRequest(BASE_URL + resource, JSON);
    }

    protected RestResponse sendGetRequest(String resource) {
        authorize();
        return rest.sendGetRequest(BASE_URL + resource, JSON);
    }

    protected RestResponse sendPostRequest(String resource, Object o) {
        authorize();
        return rest.sendPostRequest(BASE_URL + resource, JSON, RestService.getJsonFromObject(o));
    }

    protected RestResponse sendPatchRequest(String resource, Object o) {
        authorize();
        return rest.sendPatchRequest(BASE_URL + resource, JSON, RestService.getJsonFromObject(o));
    }

    private void authorize() {
        if (token == null) {
            token = AuthZ.authorize();
        }
        rest.addCustomHeaders("Authorization", token.getTokenType() + " " + token.getAccessToken());
    }

}
