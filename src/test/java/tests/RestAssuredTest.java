package tests;

import com.aventstack.extentreports.ExtentTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestAssuredTest extends BaseTest {
    private ExtentTest testLogger;

    @Test
    public void verifyPostMethod() {
        // Start logging this test
        testLogger = extent.createTest("POST API Test", "Verify POST request for creating airline details");

        // Define base URI and request body
        String baseUrl = "https://api.instantwebtools.net/v1/airlines";
        String requestBody = "{\n" +
                "    \"_id\": \"252d3bca-d9bb-476c-9a97-562d685e235c\",\n" +
                "    \"name\": \"Sri Lankan Airways\",\n" +
                "    \"country\": \"Sri Lanka\",\n" +
                "    \"logo\": \"https://upload.wikimedia.org/wikipedia/en/thumb/9/9b/Qatar_Airways_Logo.svg/sri_lanka.png\",\n" +
                "    \"slogan\": \"From Sri Lanka\",\n" +
                "    \"head_quaters\": \"Katunayake, Sri Lanka\",\n" +
                "    \"website\": \"www.srilankaaairways.com\",\n" +
                "    \"established\": \"1990\"\n" +
                "}";

        // Log the request body
        testLogger.info("Base URI: " + baseUrl);
        testLogger.info("Request Body: " + requestBody);

        // Send the POST request and log details
        testLogger.info("Sending POST request...");
        Response response = RestAssured.given()
                .log().all()
                .baseUri(baseUrl)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post()
                .then().log().all()
                .extract().response();

        // Log the response details
        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        testLogger.info("Response Status Code: " + statusCode);
        testLogger.info("Response Body: " + responseBody);

        // Verify the response status code
        Assert.assertEquals(statusCode, 200, "Expected status code was not returned!");
        testLogger.pass("POST request returned correct status code: 200.");
    }
}
