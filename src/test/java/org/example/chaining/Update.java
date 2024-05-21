package org.example.chaining;

import io.restassured.RestAssured;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Update {

    @Test
    public void updateUser(ITestContext context) {
        int id = (int) context.getAttribute("user_id");
        Map<String, String> map = new HashMap<>();
        map.put("name", "updated");
        map.put("job", "updated");
        RestAssured.baseURI = "https://reqres.in";
        given().contentType("application/json").log().uri()
                .body(map)
                .when().put("/api/users/" + id)
                .then().statusCode(200).log().all();


    }
}
