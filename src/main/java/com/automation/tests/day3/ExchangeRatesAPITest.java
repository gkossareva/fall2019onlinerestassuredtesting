package com.automation.tests.day3;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class ExchangeRatesAPITest {
    @BeforeAll
    public static void setup(){
        //FOR EVERY SINGLE REQUEST THIS IS A BASE URI
        baseURI="http://api.openrates.io";
    }
    //GET LATEST CURRENCY RATES
    @Test
    public void getLatestRates(){
//        Response response= get("/latest").prettyPeek();
//                response. then().assertThat().statusCode(200);

        Response response= given().queryParam("base","USD").when().
                get("/latest").prettyPeek();
//                response. then().assertThat().statusCode(200);

        //verify that GET request to the endpoint was successsful

        response.then().statusCode(200);
        response.then().assertThat().body("base", is("USD"));
        //IS same as EQUALS
        //IS canbe substutited to equalsTo


        //lets verify that response contains today's date
        //returns date in a required form yyyy-MM-dd
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        response.then().assertThat().body("date", containsString("2020-05-19"));
        //its same as equals
    }
    //get history of rates for2008
    @Test
    public void getHistoryOfRates(){
        Response response=given().
                               queryParam("base","USD").when().
                                  get("/2008-01-02").prettyPeek();
        Headers headers  =response.getHeaders();//response header
        response.then().assertThat().statusCode(200).and().body("date",is("2008-01-02"));
        // we can go without and() its just synthax sugar

        //rates is object and all currencies are instance variables
        //to get any property (instance variable we need to put object name.propertyname

        Float param=response.jsonPath().get("rates.EUR");
        //we use json path to parse JSON response, retrieve some values
        System.out.println( param);

        float actual=response.jsonPath().get("rates.USD");
        assertEquals(1.0,actual);
    }

}
