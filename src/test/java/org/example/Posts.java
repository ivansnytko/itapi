package org.example;

import io.restassured.RestAssured;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Posts {

    int id;

    @Test(priority = 1)
    public void createUser() {
        Map map = new HashMap<>();
        map.put("name", "abc");
        map.put("job", "leader");
        map.put("id", 7);
        RestAssured.baseURI = "https://reqres.in";
        given().contentType("application/json").log().uri()
                .body(map)
                .when().post("/api/users")
                .then().statusCode(201).log().all()
                .header("Content-Type", "application/json; charset=utf-8");
    }

    @Test(priority = 1)
    public void createUserJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "abc");
        jsonObject.put("job", "leader");
        RestAssured.baseURI = "https://reqres.in";
        given().contentType("application/json").log().uri()
                .body(jsonObject)
                .when().post("/api/users")
                .then().statusCode(201).log().all()
                .header("Content-Type", "application/json; charset=utf-8");
    }

    @Test(priority = 1)
    public void createUserPojo() {
        User user = new User();
        user.setName("pojo-it");
        user.setJob("job-it");
        RestAssured.baseURI = "https://reqres.in";
        given().contentType("application/json").log().uri()
                .body(user)
                .when().post("/api/users")
                .then().statusCode(201).log().all()
                .header("Content-Type", "application/json; charset=utf-8");
    }


}
