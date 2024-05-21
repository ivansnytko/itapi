package org.example;

import io.restassured.http.Headers;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Cookies {

    @Test
    public void testCookies() {
//        String cookie = given()
//                .when().get("https://www.google.com").getCookie("AEC");
//        System.out.println(cookie);

        Map<String, String> cookies = given()
                .when().get("https://www.google.com").getCookies();
        cookies.entrySet().stream().map(k -> k.getValue()).forEach(System.out::println);
    }

    @Test
    public void testHeaders() {
        String header = given()
                .when().get("https://www.google.com").getHeader("Content-type");
        System.out.println(header);
        System.out.println("==============");
        Headers headers = given()
                .when().get("https://www.google.com").getHeaders();
        System.out.println(headers.toString());

        given()
                .when().get("https://www.google.com")
                .then()
                .header("Content-type", "text/html; charset=ISO-8859-1")
                .header("Cache-Control", "private, max-age=0");
    }

    @Test
    public void testLogs() {
        given()
                .when().get("https://www.google.com")
                .then()
                .log().all();
    }
}
