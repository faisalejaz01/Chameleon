package com.orasi.api.restServices.salesforce.authz;

import org.testng.annotations.Test;

import com.orasi.api.restServices.Headers.HeaderType;
import com.orasi.api.restServices.ParameterBuilder;
import com.orasi.api.restServices.RestResponse;
import com.orasi.api.restServices.RestService;

public class AuthZ {

    @Test
    public static void authorize() {
        ParameterBuilder params = new ParameterBuilder();
        params.add("grant_type", "password");
        params.add("client_id", "3MVG9zlTNB8o8BA3gCW_61YbBj5YZm.0eHNfFrxZOJ8QOVWRXzvdJezJy0IqXW4B_E3obkBPW5KE5.lKEVKuo");
        params.add("client_secret", "7332830631411820973");
        params.add("username", "Justin.phlegar@orasi.com");
        params.add("password", "Saturos85!DahBpMpgBccfbEsYtxw1Xt1cP");
        RestService rest = new RestService();
        RestResponse response = rest.sendPostRequest("https://login.salesforce.com/services/oauth2/token", HeaderType.AUTH, params.build());
        System.out.println(response.getResponse());
        AuthZToken token = response.mapJSONToObject(AuthZToken.class);
        rest.addCustomHeaders("Authorization", "Bearer " + token.getAccessToken());
        String json = " {\"Name\" : \"Express Logistics and Transport\"}";
        // RestResponse avail = rest.sendGetRequest("https://na59.salesforce.com/services/data/v42.0/sobjects/Account/001f400000KLGoZAAX", HeaderType.JSON);
        // System.out.println(avail.getResponse());
        RestResponse avail = rest.sendGetRequest("https://na59.salesforce.com/services/data/v42.0/sobjects/Contact/003f400000KTgec", HeaderType.JSON);
        System.out.println(avail.getResponse());
    }
}
