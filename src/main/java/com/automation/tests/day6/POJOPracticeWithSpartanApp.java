package com.automation.tests.day6;
import com.automation.pojos.Spartan;
import com.automation.utilities.ConfigurationReader;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;


public class POJOPracticeWithSpartanApp {
    @BeforeAll
    public static void beforeAll(){
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
//        baseURI="http://54.224.118.38:8000/api";
    }
    @Test
    public void addSpartanTest(){
        Map<String,String  > spartan=new HashMap<>();
        spartan.put("gender","Male");
        spartan.put("name","Nursultan");
        spartan.put("phone","1234567890");

        Response response=given().auth().basic("admin","admin").
                          contentType(ContentType.JSON).
                          accept(ContentType.JSON).body(spartan).
                          when().get("/spartans").prettyPeek();

        response.then().statusCode(201);
        response.then().body("success",is("A Spartan is Born!"));
        Spartan spartanResponse=response.jsonPath().getObject("data",Spartan.class);
//spartanResponse is a Spartan
        System.out.println(spartanResponse instanceof Spartan);//must be true
    }
    public static void main(String[] args) {
        byte[] decoded = Base64.getDecoder().decode("YWRtaW46YWRtaW4=");
        String value = new String(decoded);
        System.out.println(value);
    }
}
