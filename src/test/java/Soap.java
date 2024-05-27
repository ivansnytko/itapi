import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Soap {

    @Test
    public void soapTest() {
        Response response = given().contentType("application/soap+xml").body("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://www.dataaccess.com/webservicesserver/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <web:NumberToDollars>\n" +
                "         <web:dNum>10</web:dNum>\n" +
                "      </web:NumberToDollars>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>")
                .when().post("https://www.dataaccess.com/webservicesserver/numberconversion.wso")
                .then().log().all().statusCode(200).extract().response();
        System.out.println(response.xmlPath().getString("Envelope.Body.NumberToDollarsResponse.NumberToDollarsResult"));
    }
}
