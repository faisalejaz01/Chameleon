package com.orasi.api.soapServices.helpers;

import com.orasi.api.soapServices.SoapService;

public class SoapTraining extends SoapService {

    public SoapTraining() {
        setServiceName("SoapTraining");
        setServiceURL("https://training-server.herokuapp.com:443/soap");
    }
}
