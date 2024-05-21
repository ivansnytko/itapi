package org.example;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Authentification {

    @Test
    public void basicAuthentification() {
        given()
                .auth().basic("postman", "password")
                .when().get("https://postman-echo.com/basic-auth")
                .then().statusCode(200).log().all();

    }

    @Test
    public void digestAuthentification() {
        given()
                .auth().digest("postman", "password")
                .when().get("https://postman-echo.com/basic-auth")
                .then().statusCode(200).log().all();

    }

    @Test
    public void preemtiveAuthentification() {
        given()
                .auth().preemptive().basic("postman", "password")
                .when().get("https://postman-echo.com/basic-auth")
                .then().statusCode(200).log().all();

    }

    @Test
    public void bearerAuthentification() {
        given()
                .header("Autorization", "Bearer token")
                .when().get("https://api.github/user/repos")
                .then().statusCode(200).log().all();

    }

    @Test
    public void oAuth1Authentification() {
        given()
                .auth().oauth("consumerKey", "consumerSecret", "access_token", "tokenSecrate")
                .when().get("https://api.github/user/repos")
                .then().statusCode(200).log().all();

    }

    @Test
    public void oAuth2Authentification() {
        given()
                .auth().oauth2("access_token")
                .when().get("https://api.github/user/repos")
                .then().statusCode(200).log().all();

    }

    @Test
    public void apiKeyAuthentification() {
        given()
                .queryParam("appid", "value")
                .when().get("https://api.github/user/repos")
                .then().statusCode(200).log().all();

    }
}
