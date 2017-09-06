package com.orasi;

import org.testng.annotations.Test;

import com.amc.api.AMC;
import com.orasi.api.restServices.ResponseCodes;
import com.orasi.api.restServices.RestResponse;
import com.orasi.utils.TestReporter;

public class Sandbox {

    @Test
    public void findAndSwitchToFrameFromOutsideFrame() {
        RestResponse rest = AMC.showtimes().getShowtime("59229828");
        TestReporter.logAPI(rest.getStatusCode() == ResponseCodes.OK, "Get all theatres", rest);

        /*
         * TheatresResponse theatres = rest.mapJSONToObject(TheatresResponse.class);
         *
         * for (Theatre theatre : theatres.getEmbedded().getTheatres()) {
         * System.out.println(theatre.getName());
         * }
         */
    }
}