package org.example;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ITParsingXML {

    @Test
    public void validateXMLIT() {
        Response response = given()
                .get("https://mocktarget.apigee.net/xml")
                .then().statusCode(200).log().all().extract().response();
        System.out.println(response.xmlPath().getString("root.city"));
        XmlPath xmlPath = new XmlPath(response.asString());
    }

//    @Test
//    public void uploadFile() {
//        given()
//                .multiPart(new File("src/test/resources/text.txt"))
//                .contentType(ContentType.MULTIPART)
//                .log().all()
//                .post("https://api.escuelajs.co/api/v1/files/upload")
//                .then().statusCode(201).log().body();
//    }
}
