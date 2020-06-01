package com.automation.tests.Homework;
import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.baseURI;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
public class Homework1 {

    @BeforeAll
    public static void beforeAll() {
//        baseURI = ConfigurationReader.getProperty("HARRYPORTER.URI");
        baseURI = "https://cybertek-ui-names.herokuapp.com/api/";
    }

    @Test
    public void test1() {
        //No params test
        // 1.Send a get request without providing any parameters
        // 2.Verify status code 200, content type application/json;
        // charset=utf-83.Verify that name, surname, gender, region fields have value
        Response response = given().accept(ContentType.JSON).when().get(baseURI);
        response.then().statusCode(200);
        assertEquals(response.contentType(), "application/json; charset=utf-8");


    }

    @Test
    public void genderTest() {
//Gender test1.Create a request by providing query parameter: gender, male or female
// 2.Verify status code 200, content type application/json; charset=utf-8
// 3.Verify that value of gender field is same from step 1
// given().accept(ContentType.JSON).pathParam("gender","female").then().statusCode(200).and()
//                .then().contentType("application/json; charset=utf-8");


        Response response = given().accept(ContentType.JSON).queryParam("gender", "female").when().
                get(baseURI).prettyPeek();
        response.then().statusCode(200).and().assertThat().contentType("application/json; charset=utf-8");
        response.then().assertThat().body("gender", is("female"));
    }

    @Test
    public void twoParamTest() {
        //2 params test1.Create a request by providing query parameters: a valid region and genderNOTE:
        // Available region values are given in the documentation
        // 2.Verify status code 200, content type application/json; charset=utf-8
        // 3.Verify that value of gender field is same from step 1
        // 4.Verify that value of region field is same from step 1

        Map<String, Object> params = new HashMap<>();
        params.put("gender", "female");
        params.put("region", "germany");
        Response response = given().accept(ContentType.JSON).queryParams(params)
                .when().get(baseURI).prettyPeek();
        response.then().statusCode(200);
        assertEquals(response.contentType(), "application/json; charset=utf-8");
        assertTrue(response.body().asString().contains("female"));
        assertTrue(response.body().asString().contains("Germany"));

    }
@Test
    public void invalidRegionTest() {
        //1.Create a request by providing query parameter: invalid region
        // 2.Verify status code 400 and status line contains Bad Request
        // 3.Verify that value of error field is Regionorlanguagenotfound
      Response response=  given().queryParam("region","moscow").when().get(baseURI);
      response.then().statusCode(400);
  String expected="Region or language not found";
    String actual=response.jsonPath().getString("error");
 assertEquals(expected,actual);


    }
    @Test
   public void amountAndregionstest(){
    // 1.Create request by providing query parameters: a valid region and amount(must be bigger than 1)
    // 2.Verify status code 200, content type application/json; charset=utf-8
    // 3.Verify that all objects have different name+surname combination
        Map<String, Object> params = new HashMap<>();
        params.put("region", "Germany");
        params.put("amount", 5);
       Response response = given().accept(ContentType.JSON).queryParams(params).when().get(baseURI).prettyPeek();
       response.then().statusCode(200).and().assertThat().contentType("application/json; charset=utf-8");
//        JsonPath jsonData=response.jsonPath();
        // 3.Verify that all objects have different name+surname combination

String name=response.body().path("name[i]");
//String surname=response.body().path("surname[0]");
   System.out.println("name = " + name);
//        System.out.println("surname = " + surname);

        }
        @Test
public void threeParamsTest(){
    Map<String, Object> params = new HashMap<>();
    params.put("gender", "female");
    params.put("region", "germany");
    params.put("amount", 2);
    Response response = given().accept(ContentType.JSON).queryParams(params)
            .when().get(baseURI).prettyPeek();
    response.then().statusCode(200);
    assertEquals(response.contentType(), "application/json; charset=utf-8");
    assertTrue(response.body().asString().contains("female"));
    assertTrue(response.body().asString().contains("Germany"));

}  }
