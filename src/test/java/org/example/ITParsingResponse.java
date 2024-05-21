package org.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ITParsingResponse {

    @Test
    void testJsonResponseIT() {
        RestAssured.baseURI = "https://reqres.in";

        Response response = given().log().all().when().get("/api/users/2").then().log().all().extract().response();
        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.getHeader("Connection"), "keep-alive");
        System.out.println("ID: " + response.jsonPath().getInt("data.id"));
        System.out.println("FIRST NAME: " + response.jsonPath().getString("data.first_name"));
    }

    @Test
    public void updateUserIt() {
        String name = "new name";
        Map<String, String> bodyInfo = new HashMap<>();
        bodyInfo.put("name", name);
        bodyInfo.put("job", "new job");
        String responseName =  given().log().all().contentType(ContentType.JSON).body(bodyInfo)
                .when().put("https://reqres.in/api/users/1").jsonPath().getString("name");
        assertEquals(name, responseName);
    }

//    @Test
//    void testJsonResponseJsonObject() {
//        RestAssured.baseURI = "https://reqres.in";
//        Response response = given().contentType(ContentType.JSON).log().uri().expect().statusCode(200).log().all()
//                .when().get("/api/users?page=2")
//                .then().extract().response();
//        JSONObject jsonObject = new JSONObject(response.asString());
//        for (int i = 0; i < jsonObject.getJSONArray("data").length(); i++) {
//            String firstName = jsonObject.getJSONArray("data").getJSONObject(i).getString("first_name");
//            System.out.println(firstName);
//        }
//    }
}
