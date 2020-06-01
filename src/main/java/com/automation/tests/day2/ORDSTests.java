package com.automation.tests.day2;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class ORDSTests {

    String  BASE_URL="http://3.90.112.152:1000/ords/hr";
    @Test
    @DisplayName("Get lis of all employees")
    public void getAllEmployees(){

      Response response=  given().baseUri(BASE_URL).when().get("/employees").prettyPeek();

    }

    @Test
    @DisplayName("Get employee under a specific ID")
    public void getOneEmployee(){
        Response response=given().baseUri(BASE_URL).when().get("/employees/{id}",100).prettyPeek();
        response.then().statusCode(200);//to verify statuscode

        int statusCode=response.statusCode(); //to save status code in variable

    }
    @Test
    @DisplayName("GET ALL COUNTRIES")
    public void getAllCountries(){
      given().
                baseUri( BASE_URL).
                when().
                get("/countries").prettyPeek().
                then().
                statusCode(200);
    }
}
