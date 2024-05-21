package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class Serialization {

    @Test(priority = 1)
    public void createUserPojo() throws IOException {
        User user = new User();
        user.setName("pojo");
        user.setJob("pojo");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/test/resources/pojo.json"), user);
        User user2 = objectMapper.readValue(json, User.class);
        System.out.println(user2.getName());
        RestAssured.baseURI = "https://reqres.in";
        given().contentType("application/json").log().uri()
                .body(user)
                .when().post("/api/users")
                .then().statusCode(201).log().all()
                .header("Content-Type", "application/json; charset=utf-8");
    }

}
