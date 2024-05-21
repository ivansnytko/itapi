package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class ITSchemaValidation {

    @Test
    public void testJsonSchemaIT() {
        RestAssured.baseURI = "https://reqres.in";
        given().log().all()
                .when().get("/api/users/2")
                .then().log().all().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonschema.json"));
    }

    @Test
    public void updateUserIt() {
        String name = "new name";
        Map<String, String> bodyInfo = new HashMap<>();
        bodyInfo.put("name", name);
        given().log().all().contentType(ContentType.JSON).body(bodyInfo)
                .when().put("https://reqres.in/api/users/1")
                .then().log().all().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("putjsonschema.json"));

    }

    @Test
    public void testXMLSchemaIT() {
        given()
                .get("https://mocktarget.apigee.net/xml")
                .then().log().all().assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("xml.xsd"));
    }

}
