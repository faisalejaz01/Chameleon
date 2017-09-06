package com.amc.api.theatres;

import com.amc.api.AMC;
import com.amc.api.theatres.showtimes.Showtimes;
import com.orasi.api.restServices.Headers.HeaderType;
import com.orasi.api.restServices.RestResponse;
import com.orasi.api.restServices.RestService;

public class Theatres {

    public Showtimes showtimes() {
        return new Showtimes();
    }

    public RestResponse getTheatres() {
        RestService rest = new RestService();
        return rest.sendGetRequest(AMC.URL + "/theatres", HeaderType.AMC);
    }
}
