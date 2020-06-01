package com.automation.tests.day7;

import com.automation.pojos.Spartan;
import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class BasicAuthentication {
@Test

public void spartanAuthentication(){
    //in the given part, we provide request specifications
//    baseURI="http://54.152.21.73:8000/api";
    baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
    given().
            auth().basic("user", "user").
            when().
            get("/spartans").prettyPeek().
            then().
            statusCode(200);
}
    @Test
    public void spartanAutharization(){
        //in the given part, we provide request specifications
//        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
        baseURI="http://54.152.21.73:8000/api";

        Spartan spartna =new Spartan ("Mamuka","Male",1234567891);
        given().
                auth().basic("user", "user").body(spartna).contentType(ContentType.JSON).
                when().
                post("/spartans").prettyPeek().
                then().
                statusCode(403);
    }
    @Test
public void authenticationTest(){
    baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
    get("/spartans").prettyPeek().then().statusCode(401);
}
}