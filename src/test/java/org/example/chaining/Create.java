package org.example.chaining;

import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Create {

    @Test(priority = 1)
    public void createUser(ITestContext context) {
        File file = new File("src/test/resources/user.json");
        RestAssured.baseURI = "https://reqres.in";
        int id = given().contentType("application/json").log().uri()
                .body(file)
                .when().post("/api/users").jsonPath().getInt("id");
//                .then().statusCode(201).log().all();
//        given().contentType("application/json").log().uri()
//                .body(file)
//                .when().post("/api/users")
//                .then().statusCode(201).log().all();

        context.setAttribute("user_id", id);


    }
}
