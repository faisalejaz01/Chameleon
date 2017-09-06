package com.amc.api.showtimes;

import com.amc.api.AMC;
import com.orasi.api.restServices.Headers.HeaderType;
import com.orasi.api.restServices.RestResponse;
import com.orasi.api.restServices.RestService;

public class Showtimes {

    public RestResponse getShowtime(String id) {
        RestService rest = new RestService();
        return rest.sendGetRequest(AMC.URL + "/showtimes/" + id, HeaderType.AMC);
    }
}
