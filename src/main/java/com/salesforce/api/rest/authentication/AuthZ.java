package com.salesforce.api.rest.authentication;

import com.orasi.api.restServices.Headers.HeaderType;
import com.orasi.api.restServices.ParameterBuilder;
import com.orasi.api.restServices.RestResponse;
import com.orasi.api.restServices.RestService;

public class AuthZ {

    public static AuthZToken authorize() {
        ParameterBuilder params = new ParameterBuilder();
        params.add("grant_type", "password");
        params.add("client_id", "3MVG9zlTNB8o8BA3gCW_61YbBj5YZm.0eHNfFrxZOJ8QOVWRXzvdJezJy0IqXW4B_E3obkBPW5KE5.lKEVKuo");
        params.add("client_secret", "7332830631411820973");
        params.add("username", "Justin.phlegar@orasi.com");
        params.add("password", "roottoor85DahBpMpgBccfbEsYtxw1Xt1cP");
        RestService rest = new RestService();
        RestResponse response = rest.sendPostRequest("https://login.salesforce.com/services/oauth2/token", HeaderType.AUTH, params.build());
        AuthZToken token = response.mapJSONToObject(AuthZToken.class);
        return token;
    }
}
