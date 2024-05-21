package org.example;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class QueryParam {

    @Test(priority = 1)
    public void testGet() {
        RestAssured.baseURI = "https://reqres.in";
        given().pathParams("mypath", "api")
                .queryParam("page", 2).log().uri()
                .when().get("{mypath}/users")
                .then().log().all().body("page", equalTo(2));

    }
}
