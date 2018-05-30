package com.chameleon.api.soapServices.helpers;

import com.chameleon.api.soapServices.SoapService;

public class SoapTraining extends SoapService {

    public SoapTraining() {
        setServiceName("SoapTraining");
        setServiceURL("https://training-server.herokuapp.com:443/soap");
    }
}
