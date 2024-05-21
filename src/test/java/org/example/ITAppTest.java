package org.example;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Unit test for simple App.
 */
public class ITAppTest {

    @BeforeMethod
    public void setUP() {
        RestAssured.baseURI = "https://reqres.in";
    }

    int id;

    @Test(priority = 1)
    public void testGet() {
        RestAssured.baseURI = "https://reqres.in";
        given().log().uri().expect().statusCode(200).log().all()
        .when().get("/api/users?page=2")
        .then().log().all().body("page", equalTo(2));
    }

    @Test
    public void getTestIt(){
        given().log().all()
        .when().get("/api/users/2")
        .then().log().all().statusCode(200).header("Connection", "keep-alive");
    }

    @Test
    public void createUserIT(){
        File file = new File("src/test/resources/json/user.json");
        given().log().all().contentType(ContentType.JSON).body(file)
        .when().post("/api/users")
        .then().log().body().statusCode(201);
    }

    @Test
    public void updateUserIt() {
        Map<String, String> bodyInfo = new HashMap<>();
        bodyInfo.put("name", "new name");
        bodyInfo.put("job", "new job");
        given().log().all().contentType(ContentType.JSON).body(bodyInfo)
                .when().put("/api/users/1")
                .then().log().body().statusCode(200);
    }

    @Test
    public void deleteUserIT() {
        given().log().uri()
                .when().delete("/api/users/2")
                .then().statusCode(204);
    }


    @Test(priority = 1)
    public void createUser() {
        File file = new File("src/test/resources/user.json");
        RestAssured.baseURI = "https://reqres.in";
//        id = given().contentType("application/json").log().uri()
//                .body(file)
//                .when().post("/api/users").jsonPath().getInt("id");
//                .then().statusCode(201).log().all();
        given().contentType("application/json").log().uri()
                .body(file)
                .when().post("/api/users")
                .then().statusCode(201).log().all();
        System.out.println("Id" + id);


    }

    @Test(priority = 2, dependsOnMethods = {"createUser"})
    public void updateUser() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "updated");
        map.put("job", "updated");
        RequestSpecification requestSpecification = given().contentType("application/json").log().uri()
                .body(map);
        ResponseSpecification responseSpecification = RestAssured.expect().statusCode(200).log().all();
        RestAssured.baseURI = "https://reqres.in";
        given().spec(requestSpecification)
                .when().put("/api/users/" + id)
                .then().spec(responseSpecification);


    }

    @Test(priority = 4, dependsOnMethods = {"createUser"})
    public void deleteUser() {
        RestAssured.baseURI = "https://reqres.in";
        given()
                .when().delete("/api/users/" + id)
                .then().statusCode(204);
    }

}
