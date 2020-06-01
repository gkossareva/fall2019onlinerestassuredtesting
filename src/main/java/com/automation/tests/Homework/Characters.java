package com.automation.tests.Homework;

import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.baseURI;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
public class Characters {
    @BeforeAll
    public static void beforeAll() {
//        baseURI = ConfigurationReader.getProperty("HARRYPORTER.URI");
        baseURI = "https://www.potterapi.com/v1";
    }

    @Test
    public void verifySortingHat() {
        //1.Send a get request to /sortingHat. Request includes :
        // 2.Verify status code 200, content type application/json; charset=utf-83.
        // Verify that response body contains one of the following houses:
        // "Gryffindor", "Ravenclaw", "Slytherin", "Hufflepuff"
        Response response = given()
                .accept(ContentType.JSON).when().get(baseURI + "/sortingHat").prettyPeek();

        assertEquals(response.statusCode(), 200);

        //idk how to compare
//  response.body().toString().contains("Hufflepuff");


    }

    @Test
    public void veryBadKey() {
        //Verify bad key  1.Send a get request to /characters. Request includes :•
        // Header Accept with value application/json•Query param key with value invalid
        // 2.Verify status code 401, content type application/json; charset=utf-83.
        // Verify response status line include message Unauthorized
        // 4.Verify that response body says"error":"APIKeyNotFound"
    }
}