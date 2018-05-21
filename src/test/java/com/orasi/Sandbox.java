package com.orasi;

import org.testng.annotations.Test;

import com.orasi.api.restServices.ParameterBuilder;
import com.orasi.api.restServices.RestResponse;
import com.orasi.api.restServices.RestService;
import com.orasi.database.Database;
import com.orasi.database.Recordset;
import com.orasi.database.databaseImpl.MySQLDatabase;
import com.orasi.utils.TestReporter;
import com.orasi.utils.dataHelpers.personFactory.Person;

public class Sandbox {

    @Test
    public void dbDemo() {
        // Build random data for post
        Person person = new Person();
        ParameterBuilder params = new ParameterBuilder();
        params.add("first_name", person.getFirstName());
        params.add("last_name", person.getLastName());

        RestService rest = new RestService();
        RestResponse response = rest.sendPostRequest("http://10.238.242.89:3000/actor", params.build());
        ActorResponse actor = response.mapJSONToObject(ActorResponse.class);

        Database db = new MySQLDatabase("10.238.242.132", "3306", "sakila");
        db.setDbUserName("sakila");
        db.setDbPassword("sakila");

        Recordset rs = new Recordset(db.getResultSet("SELECT * FROM ACTOR WHERE actor_id = " + actor.getActor().getActorId()));
        rs.print();

        TestReporter.assertEquals(person.getFirstName(), rs.getValue("first_name"), "Validate first name");
        TestReporter.assertEquals(person.getLastName(), rs.getValue("last_name"), "Validate last name");
    }

}