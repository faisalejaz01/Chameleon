package com.orasi.api.soapServices.helpers;

import java.io.File;

import javax.xml.soap.SOAPConstants;

import com.orasi.utils.XMLTools;

public class GetActorsById extends SoapTraining {
    public GetActorsById() {
        File xml = new File(this.getClass().getResource("/xmls/getActorsById.xml").getPath());
        setRequestDocument(XMLTools.makeXMLDocument(xml));

        // Generate a request from a project xml file
        setOperationName("getActorsById");
        removeComments();
        removeWhiteSpace();
    }

    public GetActorsById(String scenario, String fileType) {
        File xml = new File(this.getClass().getResource("/xmls/getActorsById.xml").getPath());
        setRequestDocument(XMLTools.makeXMLDocument(xml));
        switch (fileType.toLowerCase()) {
            case "csv":
                setRequestNodeValueByXPath(getTestScenario("/excelsheets/GetActorsById_csv.csv", scenario));
                break;

            case "xls":
                setRequestNodeValueByXPath(getTestScenario("/excelsheets/GetActorsById_xls.xls", scenario));
                break;

            case "xlsx":
                setRequestNodeValueByXPath(getTestScenario("/excelsheets/GetActorsById_xlsx.xlsx", scenario));
                break;

            default:
                break;
        }

        // Generate a request from a project xml file
        setOperationName("getActorsById");
        removeComments();
        removeWhiteSpace();
    }

    public void setActorId(String value) {
        setRequestNodeValueByXPath("/Envelope/Body/getActorsByIdRequest/actor_id", value);
    }

    public int getNumberOfResults() {
        return getNumberOfResponseNodesByXPath("/Envelope/Body/getActorsByIdResponse/actor");
    }

    public String getActorId() {
        return getRequestNodeValueByXPath("/Envelope/Body/getActorsByIdResponse/actor_id");
    }

    public String getRequestActorId() {
        return getRequestNodeValueByXPath("/Envelope/Body/getActorsByIdRequest/actor_id");
    }

    public String getActorFirstName() {
        return getRequestNodeValueByXPath("/Envelope/Body/getActorsByIdResponse/first_name");
    }

    public String getActorLastName() {
        return getRequestNodeValueByXPath("/Envelope/Body/getActorsByIdResponse/last_name");
    }

    public void setSoapVersion() {
        setSoapVersion(SOAPConstants.SOAP_1_2_PROTOCOL);
    }
}
